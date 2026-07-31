ENCODING NEDİR?

Encoding, veriyi herkese açık olarak bilinen bir yöntem (algoritma) kullanarak başka bir formata dönüştürme işlemidir. Veri kolayca eski haline getirilebilir.

Key gerektirmez çünkü veriyi çözmek için gereken tek şey, kodlama sırasında kullanılan algoritmayı bilmektir. 
Amaç; veriyi güvenli tutmak değil, farklı sistemlerin veriyi doğru şekilde okuyabilmesini sağlamaktır.


OBFUSCATION NEDİR?

Karmaşıklaştırma/Gizleme
Bu genellikle o şeyi saldırılara karşı daha dirençli hâle getirmek veya kopyalanmasını zorlaştırmak için yapılır.
Örnek: kaynak kodunun (source code) obfuscation işlemine tabi tutulmasıdır. Böylece biri yazılımı tersine mühendislik (reverse engineering) ile analiz etse bile, ürünü kopyalaması veya yeniden oluşturması daha zor olur.
Şunu unutmamak gerekir ki obfuscation güçlü bir güvenlik yöntemi değildir (doğru şekilde kullanılan şifreleme/encryption gibi). Daha çok ek bir engel oluşturur. Tıpkı encoding (kodlama) gibi, çoğu zaman aynı teknik kullanılarak geri çevrilebilir. Bazı durumlarda ise otomatik olarak çözülemez; ancak zaman alan manuel analizlerle anlaşılabilir.
Obfuscation hakkında bilinmesi gereken bir diğer önemli nokta ise, ne kadar karmaşıklaştırma yapılabileceğinin bir sınırı olduğudur. Bu sınır, gizlenen içeriğin türüne bağlıdır. Örneğin bilgisayar kodu gizleniyorsa, ortaya çıkan kodun bilgisayar tarafından hâlâ çalıştırılabilir olması gerekir. Aksi takdirde uygulama çalışmayı durdurur.


ENCODING TÜRLERİ


1.Base64 Encoding

	Amaç: Veriyi okunabilir karakterlere dönüştürmek.

Genellikle dosya, resim veya ikili(binary) veriyi metin olarak taşımak için kullanılır.

Örnek: 

    Merhaba → TWVyaGFiYQ== 

Tekrar decode edildiğinde:

    TWVyaGFiYQ==  → Merhaba


Kullanım Alanları:

	-JWT Token
	-Eposta ekleri(MIME)
	-API’lerde dosya gönderme 
	-JSON içinde resim taşıma


2. URL Encoding

URL içinde özel karakterlerin güvenli bir şekilde iletilebilmesi için bu karakterlerin belirli bir formata dönüştürülmesidir. 

URL'lerde boşluk, &, ?, =, / gibi bazı karakterlerin özel anlamları vardır. 
URL Encoding, bu karakterleri % işareti ve hexadecimal (onaltılık) değerleriyle değiştirir. 

Örnek:

Boşluk → %20 (veya form verilerinde +) 
& → %26 
? → %3F
= → %3D
ü → %C3%BC (UTF-8'e göre kodlanır)
	Orijinal metin: Ali & Veli 
	URL Encoded: Ali%20%26%20Veli 

3. Hex Encoding
Verilerin her baytını 16'lık sayı sistemi (0-9 ve A-F) kullanarak metne dönüştürme yöntemidir. 
Amaç, ikili (binary) verileri okunabilir ve taşınabilir bir metin formatına çevirmektir. 
Bir şifreleme yöntemi değildir; yalnızca veri gösterim biçimidir. 
Örnek:
Metin → Hİ
Hex →  48 69
Nerelerde kullanılır? 
Dosya ve ağ verilerini görüntülemede 
Hata ayıklama (debugging) 
Hash değerlerinin gösteriminde (ör. SHA-256 çıktıları) 
Bellek (memory) içeriklerini incelemede 

4. Binary Encoding
Verilerin bilgisayarın anlayabileceği şekilde 0 ve 1'lerden oluşan ikili (binary) biçimde gösterilmesidir. 
Reverse engineering yaparken instruation’ların binary karşılıklarını görmek gerekebilir.
Exploit geliştirirken bit seviyesinde işlem yapılır.
5.ASCII ve UTF-8
ASCII (American Standard Code for Information Interchange), karakterleri sayılarla temsil eden ilk standart karakter kodlama sistemlerinden biridir. 
7 bit kullanır ve 128 karakteri destekler.
İngilizce harfler, rakamlar ve temel noktalama işaretlerini içerir.
Türkçe karakterler (ç, ğ, ü, ş, ö, ı) gibi karakterleri desteklemez.
UTF-8 (Unicode Transformation Format - 8 bit), Unicode karakterlerini kodlamak için kullanılan en yaygın karakter kodlama standardıdır. 
1 ile 4 bayt arasında değişen uzunlukta kodlama kullanır.
Dünyadaki neredeyse tüm dilleri ve emojileri destekler.
ASCII ile uyumludur; ASCII karakterleri UTF-8'de de aynı şekilde temsil edilir.
Neden UTF-8 kullanılır? 
Türkçe dahil birçok dili destekler.
Web siteleri ve modern uygulamalarda standart karakter kodlamasıdır.
Farklı sistemler arasında karakter bozulmalarını (örneğin ü yerine ? görünmesi) önler.

CVE-2021-41773 ve CVE-2021-42013
Bu iki açık da Apache HTTP Server 2.4.49 ve 2.4.50 sürümlerinde ortaya çıkan kritik güvenlik açıklarıdır. 
CVE-2021-41773 → Path Traversal (Dizin Gezinme) ve bazı durumlarda Remote Code Execution (RCE) 
Saldırgan, özel hazırlanmış URL'ler kullanarak web dizininin dışındaki dosyalara erişebilir.
Yanlış yapılandırılmış sunucularda komut çalıştırarak sunucunun kontrolünü ele geçirebilir.
Bu açık hassas dosyaların okunabilir (ör. /etc/passwd) ve bazı durumlarda kodun uzaktan çalıştırılabilir olmasına sebep olmuştur.

CVE-2021-42013 → Path Traversal ve Remote Code Execution (RCE) 
CVE-2021-41773 açığını kapatmak için yayınlanan düzeltme eksik kaldı.
Saldırganlar bu eksikliği farklı bir yöntemle aşarak yine dizin dışındaki dosyalara erişebildi ve bazı sistemlerde uzaktan komut çalıştırabildi.
Sonucunda ilk açıktan bile daha tehlikeli hale geldi ve birçok sunucu risk altında kaldı. 

*Yapılandırmada “Require all denied” kullanılmalıdır.
*Apache’nin önce kontrol edip sonra decode ediyor olması bu açıklara sebebiyet verdi.


