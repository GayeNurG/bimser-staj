**The DFIR Report : IcedID Macro Ends in Nokoyawa Ransomware** 

**1)Zincir**

1. Phishing mail ile makrolu bir excel eki geliyor.  
2. Kullanıcı gömülü bir resme tıklayınca makro çalışıyor.  
3. Makro, IcedID DLL'ini indirip diske yazıyor.  
4. Yeniden adlandırılmış bir rundll32 ile DLL çalıştırılıyor.  
5. C2'ye bağlanıyor (kicknocisd\[.\]com).  
6. Yaklaşık 2 dk sonra otomatik discovery (whoami, systeminfo, net, nltest tarzı araçlarla).  
7. Aynı sırada scheduled task ile kalıcılık kuruluyor.  
8. Yaklaşık 2 saat sonra Cobalt Strike beacon'ları yükleniyor.  
9. Dakikalar içinde SYSTEM'e yükselme \+ LSASS dump.  
10. WMI ile Domain Controller'a lateral movement.  
11. Port 1433 (MSSQL) taraması, şifre dosyaları için paylaşım gezintisi.  
12. RDP, SMB, WinRM, PsExec (yeniden adlandırılmış) ile daha fazla lateral movement.  
13. AdFind \+ 7-Zip ile AD verisi arşivleniyor.  
14. BackConnect VNC ile elle klavye erişimi.  
15. Şifre dosyaları muhtemelen C2 kanalı üzerinden sızdırılmış.  
16. WMI \+ PsExec ile domain geneline Nokoyawa ransomware dağıtımı.

**MITRE ATT\&CK**

| Teknik Adı | ID | Gözlemlenen Davranış |
| :---- | :---- | :---- |
| Phishing: Spearphishing Attachment | T1566.001 | Makrolu Excel eki phishing mail ile geldi |
| User Execution: Malicious File | T1204.002 | Kullanıcı gömülü resme tıklayıp makroyu tetikledi |
| Ingress Tool Transfer | T1105 | Makro, IcedID DLL'ini indirdi |
| System Binary Proxy Execution: Rundll32 | T1218.011 | Yeniden adlandırılmış rundll32 ile DLL çalıştırıldı |
| Masquerading: Rename System Utilities | T1036.003 | rundll32 ve PsExec yeniden adlandırıldı |
| Application Layer Protocol: Web Protocols | T1071.001 | IcedID ve Cobalt Strike C2'si HTTP/HTTPS üzerinden |
| Account Discovery | T1087.x | Otomatik discovery ile yerel/domain hesap bilgisi toplandı |
| Scheduled Task/Job: Scheduled Task | T1053.005 | Beachhead'de kalıcılık kuruldu |
| OS Credential Dumping: LSASS Memory | T1003.001 | SYSTEM yetkisiyle LSASS dump edildi |
| Windows Management Instrumentation | T1047 | WMI ile DC'ye lateral movement ve ransomware dağıtımı |
| Remote Services: RDP / SMB / WinRM | T1021.001/002/006 | Çoklu lateral movement yöntemleri kullanıldı |
| Unsecured Credentials: Credentials In Files | T1552.001 | Paylaşımlarda şifre dosyaları arandı |
| Archive Collected Data: Archive via Utility | T1560.001 | AdFind verisi 7-Zip ile arşivlendi |
| Remote Access Software | T1219 | BackConnect VNC ile elle klavye erişimi |
| Exfiltration Over C2 Channel | T1041 | Şifre dosyalarının C2 kanalından sızdırıldığı değerlendirildi |
| Data Encrypted for Impact | T1486 | Nokoyawa ransomware ile domain şifrelendi |
| Valid Accounts | T1078 | Lateral movement boyunca geçerli hesap kimlik bilgileri kullanıldı |
| Process Injection | T1055 | Cobalt Strike beacon process injection kullandı |

**2)IOC Listesi**

| Tür | Değer |
| :---- | :---- |
| C2 (IcedID) | kicknocisd\[.\]com, 159.65.169.200 |
| C2 (IcedID, sonraki) | curabiebarristie\[.\]com, guaracheza\[.\]pics, 198.244.180.66, 45.66.248.119 |
| C2 (Cobalt Strike) | aicsoftware\[.\]com:757/8080, iconnectgs\[.\]com:8081, 5.8.18.242:443 |
| BackConnect VNC | 137.74.104.108:8080 |
| Dosya | k.exe (ransomware), p.bat, mstdc.exe (yeniden adlandırılmış PsExec), 1.dll (Cobalt Strike loader) |
| Fidye | Nokoyawa, $200.000 talep, ödenmedi |

## 

**3)Bu benim loglarımda nasıl görünürdü?**

- Makro çalıştırma → Sysmon EventID 1   
  (EXCEL.EXE → rundll32.exe ; WINWORD.EXE → mshta.exe )  
- Scheduled task → Windows Security 4698  
- LSASS dump → Sysmon EventID 10 (GrantedAccess)  
- RDP lateral movement → Windows Security 4624 (LogonType 10\)  
- PsExec → Windows Security 7045 (yeni servis kurulumu) \+ Sysmon EventID 1  
- C2 beacon → firewall/proxy log'da düzenli aralıklı dış bağlantı

**4)Tespit Düşüncesi**

Bir .exe dosyasının iki farklı adı var: dosya sistemindeki görünen adı (yeniden adlandırılabilir) ve dosyanın derlenirken içine gömülen, meta veride duran gerçek adı (OriginalFileName). Dosyayı diskte yeniden adlandırmak bu gömülü meta veriyi değiştirmiyor, çünkü bilgi dosya sisteminde değil dosyanın kendi baytlarının içinde duruyor. PsExec'in gerçek dosyası, Microsoft tarafından derlenirken içine OriginalFileName: PSEXESVC.exe diye yazılmış. Saldırgan bu dosyayı mstdc.exe adıyla kaydetse bile, dosyanın içindeki bu meta veri hâlâ PSEXESVC.exe diyor, çünkü sadece dosya adı değişti, dosya yeniden derlenmedi. Sysmon'un process oluşturma kaydında (EventID 1\) hem Image alanı (çalışan dosyanın görünen adı) hem OriginalFileName alanı (gömülü gerçek ad) ayrı ayrı loglanıyor. 

Tespit kuralı fikri: dosya adı bilinen PsExec adlarından biri değilken OriginalFileName=PSEXESVC.exe eşleşirse alarm üret, çünkü meşru bir yazılımın kendini gizlemesi için hiçbir sebep yok. 

**5)Yönetici Özeti**

Bir çalışan, sahte bir Excel dosyasındaki gömülü bir resme tıklayarak zararlı kodu tetikledi. Bu, önce gizli bir bağlantı kurulmasına, sonra saldırganların şirket ağında yayılıp yönetici yetkisi ele geçirmesine yol açtı. Altı gün sonra saldırganlar şirketin tüm bilgisayarlarını fidye yazılımıyla şifreledi ve 200.000 dolar fidye istedi; şirket ödemedi.

