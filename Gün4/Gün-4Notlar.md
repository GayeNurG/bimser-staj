ENCRYPTION

Amaç, veriyi başkalarının okuyamayacağı veya anlayamayacağı bir hâle dönüştürerek gizlilik(confidentiality) sağlamaktır. 
Veriyi yalnızca hedeflenen kişi veya kişilerin okuyabilmesini sağlar. 

Düz metin(plaintext) farklı bir biçime dönüştürülür. Secret key kullanılarak yalnızca belirli kişilerin tersine çevirebileceği şekilde bu dönüşüm yapılır.

Plaintext: Şifrelenmeden önceki, insanların okuyabildiği gerçek veridir. 
Key: Şifreleme işlemini belirleyen gizli bilgidir. Farklı key kullanılırsa farklı sonuç alınır.
Ciphertext(Şifreli Metin): Şifreleme sonucunda elde edilen, okunamayan veridir. 

Yani, başta plaintext olarak okuyabildiğimiz veri, encryption ile şifrelenerek ciphertext’e çevrilir. Ulaşılan ciphertext ise yalnızca doğru key’e sahip kişiler tarafından çözülebilir.

Encryption sonucunda;
Veri okunamaz hâle gelir.
Doğru anahtara sahip kişi veriyi geri açabilir.
Anahtar yoksa veri anlaşılamaz.

Örnek:
WhatsApp mesajları uçtan uca şifrelenir.
HTTPS ile açılan web sitelerinde internet trafiği şifrelenir.
Wi-Fi parolaları güvenli iletişim için şifreleme kullanır.

SYMMETRIC ENCRYPTION(AES)

Veriyi kilitlemek (şifrelemek) ve açmak (deşifre etmek) için tek ve aynı anahtar kullanılır.
Tarafların bu anahtarı önceden güvenli bir şekilde paylaşması gerekir.
Asimetrik şifrelemeye göre çok daha hızlıdır ve büyük verileri korumak için idealdir.
DES(Data Encryption Standard): 1970'lerde geliştirilen ve 56 bitlik bir anahtar kullanan ilk resmi standarttır. Ancak anahtar uzunluğunun kısa olması nedeniyle günümüzün işlemci hızları karşısında güvensiz hale gelmiştir ve brute-force saldırılarıyla çok kısa sürede (saatler içinde) kırılabilmektedir. 
3DES (Triple DES): DES'in zayıflıklarını gidermek için verinin art arda üç kez DES işleminden geçirilmesiyle oluşturulmuştur. DES'e göre çok daha güvenli olsa da, aynı işlemin üç kez tekrarlanması nedeniyle oldukça yavaştır. Bankacılık gibi alanlarda hala kullanılsa da yerini hızla AES'e bırakmaktadır.
AES (Advanced Encryption Standard): 2000 yılında DES'in yerini almak üzere seçilen modern ve en güvenli şifreleme standardıdır. 128, 192 ve 256 bit gibi çok daha uzun anahtarlar kullanır. Hem çok daha hızlıdır hem de mevcut teknolojilerle kırılması imkansıza yakındır (AES-128'i kırmak bile trilyonlarca yıl sürer).
Yani, DES ve 3DES yerine AES tercih edilmesinin sebepleri güvenlik yetersizliği ve hız farkıdır.

ADOBE 2013 SIZINTISI
Parolaların ECB moduyla şifrelendiği Adobe, saldırganların anahtara sahip olmadan milyonlarca parolayı çözebilmesine sebep olmuştur. Aynı parolanın her seferinde aynı şifreli çıktıyı vermesi, yaygın parolaların ve yapısal benzerliklerin kolayca tespit edilmesini sağlamıştır.
ECB(Electronic Codebook - Elektronik Kod Defteri): Düz metni bloklar halinde şifrelemek için kullanılan en basit ve temel çalışma modudur.

ASYMMETRIC ENCRYPTION
Asymmetric Encryption (Asimetrik Şifreleme), şifreleme ve şifre çözme işlemlerinde iki farklı anahtarın kullanıldığı yöntemdir. 
Bu anahtarlar:
Private Key: Sadece sahibi tarafından gizli tutulur. 
Public Key: Herkesle paylaşılabilir. 

RSA NEDİR?
RSA, en yaygın kullanılan asimetrik şifreleme algoritmalarından biridir. 
Özellikleri: 
Public ve private key kullanır.
Büyük asal sayıların çarpanlara ayrılmasının zorluğuna dayanır.
Anahtar paylaşımı ve dijital imzalarda sıkça kullanılır.

ECC NEDİR?
ECC (Elliptic Curve Cryptography), eliptik eğriler üzerine kurulu modern bir asimetrik şifreleme yöntemidir. 
RSA ile aynı amaçlar için kullanılabilir: Şifreleme, Dijital imza, Anahtar değişimi.
Ancak bunu çok daha küçük anahtarlarla yapabilir.

ECC daha kısa anahtar ile aynı güvenliği nasıl sağlıyor? 
RSA'nın güvenliği, çok büyük sayıların asal çarpanlara ayrılmasının zorluğuna dayanır.
ECC'nin güvenliği ise Eliptik Eğri Ayrık Logaritma Problemi (Elliptic Curve Discrete Logarithm Problem) adı verilen matematiksel problemin çözülmesinin çok zor olmasına dayanır.
Bu problem, RSA'nın dayandığı probleme göre daha verimlidir. Bu nedenle aynı güvenlik seviyesine ulaşmak için ECC'nin daha kısa anahtar kullanması yeterlidir.
Bu sayede ECC:
Daha az işlem gücü kullanır.
Daha az bellek tüketir.
Mobil cihazlar ve IoT cihazları için daha uygundur.
Örnek olarak 256 bitlik ECC, yaklaşık 3072 bitlik RSA kadar güvenlidir. 
Yani, ECC’nin kullandığı logaritmik çözüm RSA’ya göre daha verimli olması sebebiyle ECC’nin daha kısa anahtar kullanması yeterli gelmektedir.

DİJİTAL İMZA(DİGİTAL SİGNATURE)
Dijital imza, bir verinin:
Kim tarafından gönderildiğini doğrular (Authentication)
Değiştirilmediğini kanıtlar (Integrity)
Gönderenin daha sonra inkâr edememesini sağlar (Non-repudiation)
Yani amacı veriyi gizlemek değil, gönderenin kimliğini ve verinin bütünlüğünü doğrulamaktır.

Private Key ve Public Key çalışması:
İlk olarak mesajın hash’i alınır.
Hash, private key ile imzalanır.
Mesaj ve imza gönderilir.
Public key ile doğrulama yapılır.
Yani private key kişiye özeldir başka kişilerle paylaşılmaz. Public key ise herkese açıktır. Bu sebeple doğrulama yaparken private key’e ulaşılamayacağından ötürü public key ile işlem yapılır.

Şifreleme ile yönü neden ters?
Encryption’da amaç, kimsenin mesajı okuyamaması. Yani herkes public key ile veri şifreleyebilir ama yalnızca private key sahibi okuyabilir. 
Dijital imzada ise amaç, mesajı gizlemek değil, "Bu mesajı gerçekten ben gönderdim." demektir. Yani önemli olan, imzayı yalnızca private key sahibinin oluşturabilmesidir. Herkes public key ile bu imzanın doğru olup olmadığını kontrol edebilir. 

PGP/GPG Nedir?
PGP (Pretty Good Privacy), e-postaları ve dosyaları şifrelemek ve dijital olarak imzalamak için kullanılan bir güvenlik sistemidir. 
GPG (GNU Privacy Guard) ise PGP standardını kullanan, ücretsiz ve açık kaynaklı bir uygulamadır. 

ANAHTAR DEĞİŞİMİ ve TLS
1.Diffie-Hellman’ın Fikri
Diffie-Hellman (DH), iki tarafın önceden ortak bir anahtar paylaşmadan, internet üzerinden güvenli bir şekilde aynı gizli anahtarı oluşturmasını sağlayan bir anahtar değişim yöntemidir. 
Örnek:
Ali ve Ayşe ilk kez internette haberleşecek.
İkisinin de bir özel (private) bilgisi vardır.
Bu özel bilgiyi kimseyle paylaşmazlar.
Bunun yerine, bu özel bilgiden türettikleri bazı genel (public) değerleri birbirlerine gönderirler.
Her iki taraf da:
Kendi gizli bilgisi
Karşı tarafın gönderdiği public bilgi
ile aynı ortak anahtarı hesaplar.
Bu ortak anahtar ağ üzerinden hiç gönderilmez; iki taraf onu kendi tarafında hesaplar.
Bu yüzden araya giren biri (dinleyen saldırgan), sadece public bilgileri görse bile ortak anahtarı hesaplayamaz.

*Diffie-Hellman veriyi şifrelemez, sadece daha sonra kullanılacak ortak gizli anahtarı üretir. 

TLS neden hem simetrik hem asimetrik şifreleme kullanıyor? 
Asimetrik şifreleme (RSA/ECC), büyük sayılar üzerinde karmaşık matematiksel işlemler yaptığı için simetrik şifrelemeye göre çok daha yavaştır. Bu nedenle büyük miktarda veriyi şifrelemek verimli değildir. TLS, bu yüzden asimetrik şifrelemeyi yalnızca ortak anahtar oluşturmak için; simetrik şifrelemeyi ise tüm veri iletişimi için kullanır. 

HEARTBLEED(2014)
Heartbleed (2014), OpenSSL'in TLS Heartbeat özelliğindeki bir bellek okuma açığıdır. Saldırgan, gönderilen veriden çok daha büyük bir boyut belirterek sunucudan bellekte bulunan fazladan verileri de göndermesini sağlayabiliyordu. Bu açık sayesinde saldırganlar sunucuların belleğinden parolalar, oturum bilgileri ve hatta özel anahtarlar gibi hassas verilere erişebiliyordu(Gönderilen veri 2 byte olsun, istenen uzunluk 500 byte olsun; beklenenden parolalar, private key’ler vb. 498 byte fazla veri gönderiliyordu.). OpenSSL'in internet genelinde çok yaygın kullanılması nedeniyle bu tek hata milyonlarca sistemi etkiledi. 

Kablosuz Şifrelemenin Çöküşü
WEP (Wired Equivalent Privacy), Wi-Fi ağlarını korumak için geliştirilen ilk güvenlik protokollerinden biridir. Ancak zayıf anahtar yapısı ve kısa IV kullanması nedeniyle kolayca kırılabildiği için artık kullanılmamaktadır. WPA2, AES ile güçlü bir şifreleme sağlasa da 2017'de ortaya çıkan KRACK saldırısı, güvenlik açıklarının sadece şifreleme algoritmasından değil, protokolün çalışma şeklinden de kaynaklanabileceğini göstermiştir. Bu nedenle geliştirilen WPA3, daha güvenli anahtar değişimi ve daha güçlü kimlik doğrulama yöntemleri sunarak kablosuz ağ güvenliğini artırmayı hedeflemektedir. 

HARVEST NOW, DECRYPT LATER
Saldırganlar, bugün çözemedikleri şifreli verileri gelecekte kuantum bilgisayarlarla çözebilmek amacıyla şimdiden saklamaktadırlar. Bu riske karşı NIST'in yayımladığı FIPS 203, FIPS 204 ve FIPS 205 standartları, kuantuma dayanıklı şifreleme ve dijital imza algoritmalarıyla güvenliğin korunmasını hedeflemektedir. 

Perfect Forward Secrecy (PFS), her oturum için geçici (ephemeral) bir anahtar oluşturulan bir güvenlik özelliğidir. Böylece bir sunucunun uzun süreli özel anahtarı ele geçirilse bile, geçmişteki oturumlarda kullanılan anahtarlar farklı olduğu için eski iletişimlerin şifresi çözülemez.
Geçmiş trafik neden güvende kalır? Çünkü her bağlantıda yeni ve tek kullanımlık bir oturum anahtarı üretilir. Bir anahtarın çalınması yalnızca o anahtarla ilgili oturumu etkileyebilir; önceki veya sonraki oturumların anahtarlarına erişim sağlamaz.
