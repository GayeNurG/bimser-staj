# Volt Typhoon (AA24-038A)

**Kaynak:** https://media.defense.gov/2024/Feb/07/2003389935/-1/-1/0/CSA-PRC-Compromise-US-Critical-Infrastructure.PDF (CISA'nın kendi sitesi bot korumasıyla engellenmiş bulunmaktadır.)

## 1) Zincir ve ATT&CK

1. Kapsamlı ön keşif, hedef organizasyonun ağ topolojisi, personeli
2. Sınır cihazlarında (Fortinet, Ivanti, NETGEAR, Citrix, Cisco) bilinen/zero-day açıkları istismar ederek ilk erişim, VPN ile bağlantı
3. Ayrıcalık yükseltme açıkları ya da güvensiz saklanmış kimlik bilgileriyle admin erişimi elde etme
4. RDP ile domain controller'a ve diğer cihazlara yanal hareket
5. LOTL araçlarıyla keşif, özellikle PowerShell ile event log'ları hedefli sorgulayıp .dat dosyalarına aktarma
6. VSS (vssadmin) ile shadow copy oluşturup ntdsutil (WMIC üzerinden) ile NTDS.dit'i kopyalayarak tam domain ele geçirme
7. NTDS.dit hash'lerini çevrimdışı kırma
8. Elde edilen yetkiyle OT'ye bitişik varlıklara erişim denemesi

| Teknik | ID | Kullanım |
|---|---|---|
| Command and Scripting Interpreter: PowerShell | T1059.001 | Event log sorgulama, FRP client çalıştırma |
| Windows Management Instrumentation | T1047 | WMIC ile ntdsutil çalıştırma |
| Direct Volume Access | T1006 | vssadmin ile shadow copy oluşturma |
| OS Credential Dumping: LSASS Memory | T1003.001 | comsvcs.dll + MiniDump ile LSASS dump |
| OS Credential Dumping: NTDS | T1003.003 | NTDS.dit çıkarma |
| Indicator Removal: Clear Windows Event Logs | T1070.001 | Event log temizleme |
| Masquerading | T1036.005 | Dosya adı gizleme |
| Remote Services: RDP | T1021.001 | Domain controller'a yanal hareket |
| Archive Collected Data: Archive via Utility | T1560.001 | ronf.exe (rar.exe'nin yeniden adlandırılmışı) ile arşivleme |
| Exfiltration Over Alternative Protocol | T1048 | SMB üzerinden sızdırma |

## 2) IOC, Karışık

- **Hash'ler:** BrightmetricAgent.exe, SMSvcService.exe (FRP reverse proxy client'ları)
- **Şüpheli dosya adları:** user.dat, systeminfo.dat, rult3uil.log (C:\Windows\System32\ altında, standart olmayan konumda)
- **Standart olmayan dizinler:** C:\Windows\Temp\tmp, C:\Users\Public\ altında NTDS.dit/registry hive kopyaları
- **LOTL araç listesi:** cmd, certutil, dnscmd, ldifde, netsh, nltest, ntdsutil, PowerShell, reg, systeminfo, wevtutil, wmic, xcopy
- **ESENT Event ID'leri:** 216, 325, 326, 327 (NTDS.dit shadow copy işlemleri)

## 3) "Bu Benim Loglarımda Nasıl Görünürdü?"

- NTDS.dit shadow copy oluşturma (vssadmin) → Sysmon EventID 1 (vssadmin.exe) + Windows ESENT Application Log 216/325/326/327
- ntdsutil çalıştırma (WMIC üzerinden) → Sysmon EventID 1, wmic.exe → ntdsutil.exe
- RDP ile yanal hareket → Windows Security 4624 (LogonType 10) + Terminal Services Event ID 21-25
- PowerShell ile event log'u .dat dosyasına aktarma → Sysmon EventID 1 (Get-EventLog içeren komut satırı) + EventID 11 (dosya oluşturma)
- Event log temizleme → Windows Security 1102
- comsvcs.dll ile LSASS dump → Sysmon EventID 7 (standart olmayan bir süreçten comsvcs.dll yüklenmesi) ya da EventID 10 (lsass.exe erişimi)

## 4) Tespit Adımı: Sigma Kuralı Taslağı

Case 2'deki credential access bulgusuyla doğrudan bağlantılı olan comsvcs.dll MiniDump tekniği seçildi (Mimikatz'a alternatif, sistemin kendi DLL'ini kötüye kullanma).

```yaml
title: LSASS Memory Dump via comsvcs.dll MiniDump (Volt Typhoon LOTL)
description: Detects use of the legitimate comsvcs.dll MiniDump export to dump LSASS process memory, a living-off-the-land credential access technique used by Volt Typhoon
logsource:
    category: process_creation
    product: windows
detection:
    selection:
        CommandLine|contains|all:
            - 'comsvcs'
            - 'MiniDump'
    condition: selection
level: high
```

`category: process_creation` → Sysmon EventID 1'e karşılık geliyor. `rundll32.exe C:\Windows\System32\comsvcs.dll, MiniDump full` şeklindeki komut satırını yakalıyor. Case 2'deki Kural 3'ten (GrantedAccess=0x1410) farkı: burada komut üzerinden yakalanıyor, çünkü bu teknik doğrudan LSASS belleğine erişmek yerine meşru bir Windows DLL'inin export fonksiyonunu çağırıyor, farklı bir tespit yüzeyi.

## 5) Yönetici Özeti

Volt Typhoon, devlet destekli bir Çin siber casusluk grubu; ABD'nin kritik altyapısına (enerji, su, ulaşım, iletişim) sızıp yıllarca fark edilmeden kalmayı hedefliyor. Kötü amaçlı yazılım neredeyse hiç kullanmıyorlar, bunun yerine sistemin kendi meşru araçlarını (PowerShell, WMIC gibi) kullanarak iz bırakmamaya çalışıyorlar, bu yüzden geleneksel antivirüs imzalarıyla yakalanmaları çok zor. Amaçları veri çalmaktan çok, bir kriz anında kritik altyapıyı (örneğin su/enerji sistemlerini) devre dışı bırakabilecek bir konumda beklemek.
