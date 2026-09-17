# Case 2: Taslak Bulgular

## Zaman Çizelgesi

| Saat | Kaynak | Olay | Varlık | Durum |
|---|---|---|---|---|
| ? | mail | Fatura_2026_4471.docm eki geldi | ? | UNKNOWN |
| 09:41:12 | sysmon | WINWORD.EXE, Fatura_2026_4471.docm dosyasını açtı | FIN-WS03 / b.kaya | OBSERVED |
| 09:41:18 | sysmon | mshta.exe tetiklendi, static-cdn-analytics.com/u.hta çağrıldı | FIN-WS03 | OBSERVED |
| 09:41:19 | sysmon | powershell.exe encoded komutla başlatıldı (mshta'nın child'ı) | FIN-WS03 | OBSERVED |
| 09:41:24 | sysmon | powershell.exe, 45.137.21.88:443 (static-cdn-analytics.com) bağlantısı açtı | FIN-WS03 | OBSERVED |
| 09:41:31 | sysmon | svchost_update.exe başlatıldı (powershell'in child'ı) | FIN-WS03 | OBSERVED |
| 09:41-10:11 | firewall | 45.137.21.88'e 32 düzenli bağlantı | FIN-WS03 | OBSERVED |
| 09:51-10:05 | dns | static-cdn-analytics.com'a 4 adet TXT/NXDOMAIN sorgusu (DNS tünelleme şüphesi) | FIN-WS03 | INFERRED |
| 10:05:47 | sysmon | m.exe başlatıldı (svchost_update.exe'nin child'ı) | FIN-WS03 | OBSERVED |
| 10:05:48 | sysmon | m.exe, lsass.exe'ye erişti (GrantedAccess=0x1410), credential dumping girişimi | FIN-WS03 | OBSERVED (girişim), INFERRED (başarı) |
| 10:31:44 | auth | svc-backup hesabıyla parola ile giriş, kaynak FIN-WS03 (192.168.20.45) | app-db01 | OBSERVED |
| 10:33:02 | auth | sudo: svc-backup → root, `tar -czf /tmp/db_dump.tgz /var/lib/mysql/customers` | app-db01 | OBSERVED |
| 10:36:20 | auth | svc-backup oturumu kapandı | app-db01 | OBSERVED |
| 10:38:02 | winsecurity | SystemTelemetrySync scheduled task oluşturuldu (b.kaya), persistence, static-cdn-analytics.com/b.exe indirip çalıştırıyor | FIN-WS03 | OBSERVED |
| 11:20:37 | firewall | 480 MB (503316480 byte) transfer, hedef 185.220.101.44 | FIN-WS03 | OBSERVED (transfer), INFERRED (db_dump.tgz ile bağlantısı) |

## IOC Listesi

| Tip | Değer | Değerlendirme | Kanıt |
|---|---|---|---|
| Domain | static-cdn-analytics.com | Confirmed malicious (C2 + persistence kaynağı) | dns, sysmon, winsecurity |
| IP | 45.137.21.88 | Confirmed malicious (C2/beaconing hedefi) | firewall, dns, sysmon |
| IP | 185.220.101.44 | Suspicious (muhtemel exfiltration hedefi) | firewall |
| Dosya | Fatura_2026_4471.docm | Confirmed malicious (initial delivery) | sysmon |
| URL | http://static-cdn-analytics.com/u.hta | Confirmed malicious | sysmon |
| URL | http://static-cdn-analytics.com/b.exe | Confirmed malicious (persistence payload) | winsecurity (decoded task content) |
| Dosya adı | svchost_update.exe (Temp altında) | Confirmed malicious (masquerading) | sysmon |
| Dosya adı | m.exe (Temp altında) | Confirmed malicious (credential dumping aracı) | sysmon |
| Scheduled Task | SystemTelemetrySync | Confirmed malicious (persistence) | winsecurity |
| Hesap | b.kaya | Kullanılan/muhtemelen ele geçirilen hesap (FIN-WS03) | sysmon, winsecurity |
| Hesap | svc-backup | Kullanılan hesap (app-db01), meşru mü kötüye mi kullanılmış netleşecek | auth |
| Host | FIN-WS03 (192.168.20.45) | Ele geçirilmiş host | tüm kaynaklar |
| Dosya | /tmp/db_dump.tgz (app-db01) | Muhtemel exfiltration hazırlığı, bağlantısı henüz kesinleşmedi | auth |

## MITRE ATT&CK Eşlemesi

| Teknik Adı | ID | Gözlemlenen Davranış | Confidence |
|---|---|---|---|
| Phishing: Spearphishing Attachment | T1566.001 | Fatura_2026_4471.docm eki | Düşük, mail logu henüz doğrulanmadı |
| User Execution: Malicious File | T1204.002 | Kullanıcının .docm dosyasını açması | Orta |
| System Binary Proxy Execution: Mshta | T1218.005 | mshta.exe ile uzaktan .hta çalıştırma | Yüksek |
| Command and Scripting Interpreter: PowerShell | T1059.001 | Encoded powershell komutu | Yüksek |
| Application Layer Protocol: Web Protocols | T1071.001 | powershell.exe'nin HTTPS (443) üzerinden C2 | Yüksek |
| Application Layer Protocol: DNS | T1071.004 | TXT/NXDOMAIN sorguları, DNS tünelleme şüphesi | Orta, kesinleşmedi |
| Scheduled Task/Job: Scheduled Task | T1053.005 | SystemTelemetrySync görevi | Yüksek |
| OS Credential Dumping: LSASS Memory | T1003.001 | m.exe → lsass.exe erişimi, GrantedAccess=0x1410 | Yüksek (girişim), başarı doğrulanmadı |
| Valid Accounts | T1078 | svc-backup hesabıyla FIN-WS03'ten app-db01'e giriş | Orta, lateral movement bağlantısı netleşiyor |
| Archive Collected Data | T1560 | `tar -czf db_dump.tgz` | Orta |
| Exfiltration Over C2 Channel | T1041 | 480 MB transfer, 185.220.101.44 | Düşük, db_dump.tgz ile bağlantısı henüz kanıtlanmadı |
