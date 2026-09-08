# CISA – Emotet Kötü Amaçlı Yazılımı (AA20-280A)

## 1) Zincir ve ATT&CK

Phishing mail, ekli Word belgesi (.doc) ya da kötü niyetli link ile geliyor.
Kullanıcı eki açıyor / linke tıklıyor, makro tetikleniyor.
Gizlenmiş (obfuscate edilmiş) VBA makro, PowerShell'i genelde cmd.exe üzerinden çağırıp ek payload indiriyor.
Malware, bilinen bir Emotet sunucusuna (domain ya da IP) HTTP POST isteği gönderiyor. Ama istek attığı adres normal bir web sitesi yolu gibi görünmüyor, mesela /kdjfhskjdfh/xmnbvcxznm/ gibi, anlamsız harflerden oluşan, rastgele uzunlukta klasör isimleri kullanılıyor. Bu tesadüfi görünüm aslında bilinçli, amaç trafiği meşru bir web isteğiymiş gibi göstermek ama aynı zamanda tahmin edilemez bir desen kullanarak imza tabanlı tespitten kaçmak.
Kalıcılık üç ayrı yöntemle kurulabiliyor: yeni Windows servisi, Registry Run key, ya da scheduled task.
Yerel admin şifresi kaba kuvvetle (brute force) ele geçiriliyor, başarılı olursa Admin$ paylaşımı üzerinden SMB ile ağda yayılıyor, bu "solucan benzeri" özelliği.
Mimikatz gibi parola yakalama modülleri düşürülüyor, LSASS belleğinden şifre okunuyor.
Outlook'tan mail adresleri ve mail verisi toplanıyor, şifreleniyor, çerezler içine gizlenip HTTP GET ile C2'ye sızdırılıyor.

| Teknik Adı | ID | Kullanım |
|---|---|---|
| Kimlik Avı: Hedefli Kimlik Avı Eki | T1566.001 | Ekler içeren kimlik avı mailleriyle dağıtım |
| Kimlik Avı: Hedefli Kimlik Avı Bağlantısı | T1566.002 | Bağlantı içeren kimlik avı mailleriyle dağıtım |
| Kullanıcı Tarafından Yürütme: Zararlı Dosya | T1204.002 | Kullanıcının eki açması |
| Kullanıcı Tarafından Yürütme: Kötü Amaçlı Bağlantı | T1204.001 | Kullanıcının linke tıklaması |
| Komut ve Betik Yorumlayıcısı: Visual Basic | T1059.005 | Word makrosu, ek payload indirme scripti çalıştırıyor |
| Komut ve Betik Yorumlayıcısı: PowerShell | T1059.001 | Payload indirme, Mimikatz gibi ek kaynak çekme |
| Komut ve Betik Yorumlayıcısı: Windows Komut Kabuğu | T1059.003 | cmd.exe, PowerShell scriptini çalıştırmak için |
| Gizlenmiş Dosyalar veya Bilgiler | T1027 | Makrolarda URL/cmd/PowerShell komutlarını gizleme |
| Gizlenmiş Dosyalar veya Bilgiler: Yazılım Paketleme | T1027.002 | Payload'ları korumak için özel paketleme |
| Uygulama Katmanı Protokolü: Web Protokolleri | T1071.001 | C2, HTTP POST üzerinden |
| Standart Olmayan Bağlantı Noktası | T1571 | HTTP, 20/22/7080/50000 gibi standart olmayan portlarda |
| Şifreli Kanal: Asimetrik Kriptografi | T1573.002 | C2 trafiği RSA anahtarlarıyla şifreli |
| Sistem İşlemi Oluşturma: Windows Hizmeti | T1543.003 | Kalıcılık, yeni servis oluşturma |
| Önyükleme Otomatik Başlatma: Kayıt Defteri Run Key | T1547.001 | Kalıcılık, Run key |
| Planlanmış Görev/İş | T1053.005 | Kalıcılık, zamanlanmış görev |
| Kaba Kuvvet: Şifre Tahmini | T1110.001 | Yerel admin şifresi kaba kuvvet |
| Geçerli Hesaplar: Yerel Hesaplar | T1078.003 | Kaba kuvvetle elde edilen şifreyle yayılma |
| Uzaktan Hizmetler: SMB/Windows Yönetici Paylaşımları | T1021.002 | Admin$ paylaşımı üzerinden yatay hareket |
| Uzaktan Hizmetlerin İstismarı | T1210 | Bazı vakalarda ETERNALBLUE/SMB açığı |
| İşletim Sistemi Kimlik Bilgisi Dökümü: LSASS Belleği | T1003.001 | Mimikatz ile LSASS dump |
| Parola Depolarından Kimlik Bilgileri: Web Tarayıcıları | T1555.003 | Tarayıcı şifre yakalama modülü |
| Güvensiz Kimlik Bilgileri: Dosyalardaki Kimlik Bilgileri | T1552.001 | Oturum açmış kullanıcının şifrelerini alan modül |
| Hesap Keşfi: E-posta Hesabı | T1087.003 | Outlook'tan mail adresi toplama |
| E-posta Toplama: Yerel E-posta Toplama | T1114.001 | Outlook mail verisi toplama |
| Arşivden Toplanan Veriler | T1560 | Toplanan veri gönderilmeden önce şifreleniyor |
| C2 Kanalı Üzerinden Süzme | T1041 | Sistem bilgisi, HTTP GET çerezleri içinde sızdırılıyor |
| Süreç Enjeksiyonu: DLL Enjeksiyonu | T1055.001 | explorer.exe ve diğer süreçlere enjeksiyon |
| Süreç Keşfi | T1057 | Yerel süreçleri sayma |
| Ağ İzleme | T1040 | Ağ trafiğini izlemek için ağ API'lerine müdahale |
| Windows Yönetim Araçları | T1047 | WMI ile powershell.exe çalıştırma |

28 teknik var, tek bir olaydan değil, yıllar boyunca gözlemlenen farklı Emotet kampanyalarının toplamından geliyor.

## 2) IOC — Yöntem Düzeyinde

- **Dosya türleri:** .doc (ilk ek), sonradan .zip (parola korumalı, mail gateway'i atlatmak için)
- **Portlar/protokol:** HTTP, ağırlıklı 80/8080/443, ayrıca standart olmayan 20/22/7080/50000; SMB için 445
- **URL deseni:** C2 istekleri /wp-content/###/ ve /wp-admin/###/ gibi WordPress dizinlerini taklit eden, anlamsız rastgele uzunlukta yollar kullanıyor
- **User-Agent:** Eski/sahte bir tarayıcı imzası (Mozilla/4.0, MSIE 7.0, Windows NT 6.1). Gerçek trafikte artık nadiren görülen, dolayısıyla şüpheli bir sabit değer
- **Registry artefaktı:** HKEY_CURRENT_USER\Software\Microsoft\Windows\CurrentVersion\Run

## 3) "Bu Benim Loglarımda Nasıl Görünürdü?"

- Makro çalıştırma → Sysmon EventID 1 (WINWORD.EXE → cmd.exe/powershell.exe)
- Registry Run key kalıcılığı → Sysmon EventID 13
- Scheduled task → Windows Security 4698
- Yeni servis (persistence) → Windows Security 7045
- Kaba kuvvet + SMB lateral movement → Windows Security 4625 (başarısız denemeler), sonra 4624 LogonType 3, Sysmon EventID 3 → 445 portu
- LSASS/Mimikatz → Sysmon EventID 10
- C2 trafiği standart olmayan portlarda → firewall/proxy log'da HTTP'nin beklenmedik bir portta (7080, 50000 gibi) görülmesi kendi başına anomali

## 4) Yönetici Özeti

Emotet, sahte bir e-postaya eklenmiş bir Word belgesiyle bir bilgisayara bulaşan, sonra kendi kendine şirket ağındaki diğer bilgisayarlara da yayılabilen bir zararlı yazılım. Sadece veri çalmakla kalmıyor, aynı zamanda başka saldırganlara "bu bilgisayara zaten girdik, devamını siz getirin" diyerek erişim satan bir altyapı olarak da çalışıyor. 2021'de uluslararası bir operasyonla kapatılmasına rağmen aynı yıl geri döndü, bu da böyle altyapıların tamamen yok edilmesinin ne kadar zor olduğunu gösteriyor.
