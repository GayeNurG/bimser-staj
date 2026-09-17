# Ağ Segmentasyonu

Kurumsal bir ağ tek parça değil, farklı bölümlere (muhasebe, sunucular, misafir ağı gibi) ayrılıyor. Bunun amacı hem güvenlik hem performans. Güvenlik açısından, bir bölüm ele geçirilse bile saldırgan direkt diğer bölümlere geçemiyor, segmentler arasına konan firewall bir nevi engel görevi görüyor.

**Örnek:** Misafir Wi-Fi ağı ile şirketin iç sunucu ağı ayrı segmentlerde olursa, misafir ağındaki bir cihaz ele geçirilse bile saldırgan doğrudan sunuculara erişemez.

**Analist için önemi:** Segmentasyonun kendisi log üretmiyor ama segmentler arası geçişleri kontrol eden firewall'lar önemli bir log kaynağı. Bir saldırganın segmentler arası geçiş denemesi (yani yanal hareket) buradaki loglardan görülebilir.

# VLAN (Virtual LAN)

VLAN, fiziksel olarak aynı switch/ağ altyapısına bağlı cihazları, mantıksal olarak ayrı ağlarmış gibi bölmemizi sağlıyor. Yani kablolama değişmeden, yazılımsal olarak "bu cihazlar bir grup, şunlar başka bir grup" diye ayırabiliyoruz. Segmentasyonun pratikte uygulanma yöntemlerinden biri VLAN.

**Örnek:** Aynı switch üzerinde muhasebe departmanının bilgisayarları VLAN 10'da, İK departmanının bilgisayarları VLAN 20'de olabilir; fiziksel olarak aynı switch'e bağlılar ama birbirlerinin trafiğini normalde göremezler.

**Analist için önemi:** VLAN'lar arası geçiş yine router/firewall (Layer 3 cihaz) üzerinden olur, bu yüzden VLAN'lar arasındaki trafiği görmek istiyorsak sensörü o geçiş noktasına (router/firewall) koymamız gerekir. Ayrıca switch'lerin kendisi de port bazlı loglama yapabilir.

# DMZ (Demilitarized Zone)

DMZ, dışarıya (internete) açık olması gereken sunucuların (web sunucusu, mail sunucusu gibi) konulduğu, iç ağdan izole edilmiş bir ara bölge. Amaç, dışarıdan erişilebilir bir sistem ele geçirilse bile saldırganın doğrudan iç ağa (kritik verilerin, kullanıcı makinelerinin olduğu yere) sıçramasını engellemek.

**Örnek:** Şirketin herkese açık web sitesi DMZ'de barındırılır. İç ağdaki çalışan bilgisayarları ve dosya sunucuları ise DMZ'nin arkasında, ayrı ve daha korumalı bir bölgede kalır. DMZ'deki web sunucusu ele geçirilse bile, saldırganın iç ağa geçmesi için ayrıca bir firewall engelini aşması gerekir.

**Analist için önemi:** DMZ'ye giren/çıkan trafik genelde iki firewall arasından geçer (internet-DMZ arası ve DMZ-iç ağ arası), bu yüzden sensörler bu iki geçiş noktasına konulmalı. DMZ'deki bir sunucuya yapılan saldırı ile iç ağa yapılan yanal hareket, farklı firewall loglarında görünür; biri dış saldırıyı, diğeri saldırganın iç ağa geçme denemesini gösterir.

# Proxy

Proxy, kullanıcıların internete (veya bir sunucuya) doğrudan değil, ara bir sunucu üzerinden erişmesini sağlıyor. İki türü var:
- **Forward proxy:** İç ağdaki kullanıcıların dışarıya çıkışını bir noktadan geçirir.
- **Reverse proxy:** Dışarıdan gelen isteklerin iç sunuculara ulaşmadan önce geçtiği nokta.

Amaç; trafiği tek bir noktadan kontrol etmek, filtrelemek ve kaydetmek.

**Örnek:** Şirket çalışanları internete çıkarken hepsi forward proxy üzerinden geçiyor olabilir; böylece hangi kullanıcı hangi siteye gitti bilgisi tek bir yerde toplanır, zararlı bir siteye erişim burada engellenebilir. Reverse proxy'ye örnek olarak da, dışarıdan web sitesine gelen tüm istekler önce reverse proxy'den geçip sonra gerçek web sunucusuna yönlendirilebilir.

**Analist için önemi:** Proxy, tek başına çok değerli bir log kaynağı çünkü tüm kullanıcı-internet trafiği (forward proxy) veya dışarıdan-içeriye tüm istekler (reverse proxy) buradan geçiyor. Sensörü proxy'nin üzerine koymak, tek bir noktadan geniş bir görünürlük kazandırır; hangi kullanıcı hangi domaine, ne zaman, hangi sonuçla (izin verildi/engellendi) gitti gibi bilgiler burada tutulur.

# VPN (Virtual Private Network)

VPN, güvenli olmayan bir ağ (internet gibi) üzerinden, iki nokta arasında şifreli bir "tünel" kurarak sanki aynı özel ağdaymış gibi bağlantı sağlıyor. En yaygın kullanımı, uzaktan çalışan personelin evden şirketin iç ağına güvenli şekilde bağlanması. Trafik şifrelendiği için araya giren biri içeriği okuyamıyor.

**Örnek:** Evden çalışan bir personel, şirketin iç ağındaki dosya sunucusuna erişmek için önce VPN bağlantısı kuruyor. Bağlantı kurulduktan sonra, sanki fiziksel olarak ofisteymiş gibi iç kaynaklara erişebiliyor, ama tüm trafik şifreli tünelden geçiyor.

**Analist için önemi:** VPN sunucusu/gateway'i önemli bir log kaynağı; kimin ne zaman bağlandığı, hangi IP'den bağlandığı, bağlantının ne kadar sürdüğü gibi bilgiler burada tutulur. Özellikle normal dışı saatlerde veya alışılmadık bir konumdan yapılan VPN girişleri, ele geçirilmiş bir hesabın habercisi olabilir; bu yüzden VPN logları kimlik bilgisi kötüye kullanımını (credential abuse) tespit etmede kritik.

# SIEM (Security Information and Event Management)

SIEM, farklı kaynaklardan (sunucular, firewall, proxy, VPN, uç noktalar) gelen tüm logları tek bir merkezde toplayan ve analiz eden bir sistem. Dört temel işlevi var:

1. **Toplama (Collection):** Farklı kaynaklardan (Linux auth.log, Windows Event Log, firewall, proxy vs.) logları çekip tek bir yere getirme.
2. **Normalleştirme (Normalization):** Her kaynağın kendi log formatı farklı olduğu için (biri syslog, biri Windows Event XML, biri CSV gibi), bunları ortak/standart bir yapıya çevirme. Böylece "kullanıcı adı" alanı her kaynakta farklı isimle geçse bile SIEM içinde tek bir alan olarak aranabilir hale gelir.
3. **Korelasyon (Correlation):** Farklı kaynaklardaki olayları birbirine bağlama. Örneğin VPN logunda bir kullanıcının olağandışı saatte giriş yapması ile aynı kullanıcının dosya sunucusunda çok sayıda dosyaya erişmesi ayrı ayrı normal görünebilir ama birlikte değerlendirilince şüpheli bir örüntü ortaya çıkabilir.
4. **Uyarı Üretme (Alerting):** Belirli koşullar sağlandığında (örneğin bir hesaba art arda 10 başarısız giriş) otomatik alarm üretme.

**Örnek:** Lab'ımda SSH brute force denemesini görmek için Ubuntu'daki auth.log'a tek tek bakmam gerekti. Kurumsal bir ortamda, yüzlerce sunucunun auth.log'una tek tek bakmak yerine, hepsi SIEM'e akar; ben tek bir arayüzden "başarısız giriş" araması yaparak hepsini aynı anda görebilirim.

**Analist için önemi:** SIEM olmadan, bir saldırının farklı kaynaklardaki izlerini (firewall'da bir bağlantı denemesi, proxy'de bir domain erişimi, endpoint'te bir process create) ayrı ayrı, elle birleştirmek gerekir; bu hem yavaş hem de büyük olayları kaçırma riski taşır. SIEM bu parçaları tek yerde birleştirdiği için olayın bütününü görmeyi mümkün kılar.

## Neden Her Makineye Tek Tek Bakmak Yerine SIEM Kullanırız, Bunun Bedeli Nedir?

Her makineye tek tek bakmak, birkaç makine için mümkün olabilir ama yüzlerce/binlerce cihazın olduğu bir kurumda imkânsız hale gelir. Hem zaman açısından hem de olaylar arasındaki bağlantıyı elle kurmanın neredeyse imkânsız olması açısından. SIEM bu sorunu çözüyor: tek bir yerden arama, korelasyon ve (varsa) otomatik uyarı imkânı sağlıyor. Ama bunun bir bedeli var:

- **Kurulum ve bakım maliyeti:** SIEM'e her kaynağı bağlamak, normalleştirme kurallarını doğru ayarlamak zaman ve uzmanlık gerektiriyor.
- **Depolama/işlem maliyeti:** Tüm logları merkezi bir yerde tutmak ciddi disk alanı ve işlem gücü istiyor (Splunk Free'nin günlük 500 MB limiti var).
- **Yanlış pozitif/gürültü riski:** Çok fazla log tek yerde toplanınca, doğru korelasyon kuralları yazılmazsa önemli olaylar gürültüye karışabilir.
- **Tek nokta arızası (single point of failure):** SIEM çökerse veya yanlış yapılandırılırsa, merkezi görünürlük de kaybolur.

# Splunk Kavramlar

## Index (İndeks)

Index, Splunk'ın içine aldığı verileri fiziksel olarak depoladığı yer. Splunk'a gelen her log, bir index'e yazılır; varsayılan olarak "main" index'e gider ama farklı veri türlerini birbirinden ayırmak için özel index'ler de oluşturulabilir. Bir nevi verinin kaydedildiği "klasör" gibi düşünülebilir, ama aslında Splunk'a özel optimize edilmiş bir veri yapısı.

**Analist için önemi:** Index'lere ayırmak, hem arama performansını artırıyor (gereksiz veride arama yapmıyorsun) hem de erişim yetkilerini kaynak bazlı sınırlamayı mümkün kılıyor. Mesela sadece belirli bir index'e erişim izni verilebiliyor.

## Source (Kaynak)

Source, verinin geldiği asıl dosya veya konum. Yani "bu log nereden geldi" sorusunun cevabı. Genelde tam dosya yolu şeklinde görünür.

**Analist için önemi:** Bir olayı incelerken "bu bilgi tam olarak hangi dosyadan/kaynaktan geldi" sorusunun cevabını source alanı veriyor. Bu da bulgunun doğruluğunu teyit etmek ve gerekirse orijinal dosyaya geri dönüp bakmak için önemli.

## Sourcetype (Kaynak Türü)

Sourcetype, verinin formatını/türünü tanımlıyor. Splunk'a "bu veriyi nasıl yorumlaman, hangi alanlara ayırman gerekiyor" bilgisini veriyor. Aynı source'tan gelen veri bile farklı sourcetype'larla etiketlenebilir, ama genelde her log türünün kendine has bir sourcetype'ı olur (örneğin `linux_secure`, `WinEventLog:Sysmon` gibi).

**Analist için önemi:** Doğru sourcetype atanmazsa Splunk veriyi yanlış ayrıştırabilir (parse), alanlar (fields) doğru çıkmaz ve arama/analiz zorlaşır. Sourcetype, aslında normalleştirme işleminin (SIEM'in dört işlevinden biri) pratikte nasıl gerçekleştiğini gösteren somut bir kavram.
