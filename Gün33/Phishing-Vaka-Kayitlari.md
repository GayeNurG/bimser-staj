**PHISH-2026-0909-01: Sample-10**

**Vaka Numarası:** PHISH-2026-0909-01

**Açılış Tarihi ve Durum:**

Açılış: 2026-09-09  
Durum: Kapalı (kullanıcıya bildirildi, aksiyon gerekmiyor)

**TLP Etiketi:**

TLP:CLEAR (herkese açık araştırma örneği, kurum içi veri yok)

**Kısa Özet:**

Microsoft kimliğine bürünen, sahte "olağandışı oturum açma" uyarısı. Hedef sahte bir giriş sayfası değil, kullanıcıyı saldırganın Gmail adresiyle yazışmaya çekmek.

**Gözlemlenebilirler:**

•  From domain: access-accsecurity\[.\]com  
•  Return-Path domain: thcultarfdes.co\[.\]uk  
•  Gönderen IP: 89.144.44\[.\]2  
•  Hedef mailto adresi: sotrecognizd@gmail\[.\]com

**Zenginleştirme Sonuçları:**

•  Domainler VirusTotal'da temiz (yeni altyapı olabilir)  
•  Gönderen IP, VirusTotal'da geçmişte 2 zararlı dosyanın C2 iletişiminde kullanılmış

**Karar:**

Oltalama (phishing), e-posta kimlik doğrulama kontrolleri başarısız: SPF none, DKIM none, DMARC permerror. 
From/Return-Path uyuşmuyor, hedef kimlik bilgisi toplamaya yönelik sosyal mühendislik. 

Kullanıcıya bildirim yapıldı, ek aksiyon gerekmiyor (dosya/kimlik bilgisi paylaşımı yok).

**PHISH-2026-0909-02 : Sample-95**

**Vaka Numarası:** PHISH-2026-0909-02

**Açılış Tarihi ve Durum:**

Açılış: 2026-09-09   
Durum: Kapalı (kullanıcıya bildirildi)

**TLP Etiketi:**

TLP:CLEAR

**Kısa Özet:**

Trustwallet kimliğine bürünen, meşru mail altyapısı (Mailgun/Gorgias) üzerinden gönderilmiş kripto cüzdanı oltalaması. Hedef doğrulanmış phishing sayfası.

**Gözlemlenebilirler:**

•  From domain: emails.gorgias[.]com  
•  Return-Path domain: gorgias[.]io  
•  Gönderen IP: 143.55.227[.]147  
•  Yönlendirici: usertest.sciquest[.]com  
•  Asıl hedef: drop-coin-availablenow.site44[.]com

**Zenginleştirme Sonuçları:**

•  Gönderen IP AbuseIPDB'de whitelisted (Mailgun)  
•  Asıl hedef VirusTotal'da 10/89 motor tarafından Phishing/Malicious doğrulanmış, 19 gün önce aktif  
•  Yönlendirici temiz ama geçmişte kötüye kullanılmış

**Karar:**

Doğrulanmış oltalama, dış kaynak (VirusTotal) doğrulaması var.   
DMARC fail, From/Return-Path uyumsuzluğu ve hedef URL'nin VirusTotal'daki phishing tespiti birlikte değerlendirildi.

Kullanıcıya bildirim yapıldı; cüzdan bilgisi girildiyse ek aksiyon gerekir (bkz. kullanıcı bildirimi).

**PHISH-2026-0909-03 : Sample-800**

**Vaka Numarası:** PHISH-2026-0909-03

**Açılış Tarihi ve Durum:**

Açılış: 2026-09-09   
Durum: Kapalı (kullanıcıya bildirildi)

**TLP Etiketi:**

TLP:CLEAR

**Kısa Özet:**

BNB/Binance airdrop kimliğine bürünen kripto oltalaması. Gerçek Microsoft 365 altyapısından (kötüye kullanılmış geçici tenant) gönderilmiş, link metninde homoglyph hilesi var.

**Gözlemlenebilirler:**

•  From/Return-Path domain: yx2nqoz.onmicrosoft[.]com  
•  Gönderen IP: 40.107.223[.]119  
•  Yönlendirici: click.pstmrk[.]it  
•  Asıl hedef: appbnb.web[.]app

**Zenginleştirme Sonuçları:**

•  Gönderen IP AbuseIPDB'de 35 kez raporlanmış ama %0 confidence (paylaşımlı Microsoft altyapısı)  
•  Asıl hedef VirusTotal'da temiz ama 3 yıldır yeniden analiz edilmemiş, dış doğrulama zayıf  
•  Yönlendirici temiz ama en az 10 zararlı dosyaya gömülü geçmişi var

**Karar:**

Oltalama, dış itibar servisleri zayıf/karışık sinyal verdi, karar iç kanıtlara dayanıyor:   
DKIM yok, DMARC sadece tahmin (bestguesspass), link metninde Yunanca omicron ile homoglyph hilesi, gövde metni istatistiksel filtre atlatmak için anlamsızca tekrarlanmış. 

Kullanıcıya bildirim yapıldı.

