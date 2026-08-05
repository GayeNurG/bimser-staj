IPv4 Adresinin Yapısı

IPv4 (Internet Protocol Version 4), ağdaki cihazların birbirini bulabilmesi için kullanılan 32 bitlik bir adresleme sistemidir. Her cihaza benzersiz bir IP adresi atanır. 

Bir IPv4 adresi 4 oktetten (8'er bit) oluşur. Her oktetin değeri 0 ile 255 arasında olabilir. 

Bir IP adresi hiyerarşik olarak iki bölümden oluşur:

Network ID (Ağ Numarası): Adresin hangi ağa ait olduğunu tanımlar.

Host ID (Bilgisayar/Düğüm Numarası): O ağ içindeki belirli bir cihazı temsil eder.

Subnet Mask (Alt Ağ Maskesi): IP adresinin hangi kısmının ağ, hangi kısmının cihaz (host) olduğunu belirleyen 32 bitlik bir dizidir. Örneğin, 255.255.255.0 maskesi ilk 24 bitin ağa, kalan 8 bitin ise cihazlara ayrıldığını gösterir.

Adres Sınıfları: IPv4 mimarisi, yönetimi kolaylaştırmak için adresleri A, B, C, D ve E olmak üzere beş sınıfa ayırmıştır. A, B ve C sınıfları genel ağlarda kullanılırken; D sınıfı Multicast (çoklu yayın), E sınıfı ise deneysel amaçlar için rezerve edilmiştir.

IPv4 Başlık (Header) Yapısı: Veri iletimi sırasında her IP paketinin başında kontrol bilgilerini içeren bir başlık bulunur. Bu yapı; sürüm bilgisi (IPv4 için 4), TTL (Yaşam Süresi), protokol türü, kaynak ve hedef IP adresleri gibi verileri içeren standart olarak 20 bayt büyüklüğünde bir alandır.

IPv4 protokolü, bu yapı sayesinde teorik olarak yaklaşık 4,3 milyar benzersiz adres üretebilmektedir.

Public IP ve Private IP

Public IP:
İnternet üzerinde benzersiz olan ve herkes tarafından erişilebilen IP adresidir.
İnternet Servis Sağlayıcısı (ISP) tarafından atanır.
Dünya üzerinde tektir.
İnternetteki diğer cihazlarla doğrudan iletişim kurabilir.
Private IP:
Yalnızca yerel ağlarda kullanılan IP adresidir.
Ev, okul veya şirket ağlarında kullanılır.
İnternet üzerinde yönlendirilmez (routable değildir).
Aynı private IP farklı ağlarda tekrar kullanılabilir.
Örneğin, hem bir evdeki bilgisayar hem de başka bir şirketin bilgisayarı 192.168.1.10 adresini kullanabilir. Çünkü bu adresler yalnızca kendi yerel ağları içinde anlamlıdır.

Private IP Aralıkları:
Sınıf A: 10.0.0.0 – 10.255.255.255  → Büyük şirket ağları
Sınıf B: 172.16.0.0 – 172.31.255.255   → Orta büyüklükte ağlar
Sınıf C: 192.168.0.0 – 192.168.255.255   → Ev ve küçük ofis ağları
*Ek olarak, DHCP sunucusunun çalışmadığı durumlarda cihazların otomatik olarak aldığı APIPA aralığı (169.254.0.1 - 169.254.255.254) da private bir aralıktır.

Public ve Private IP Neden Önemlidir? 

IP Adresi Tasarrufu: IPv4 protokolü yaklaşık 4,3 milyar benzersiz adres üretebilir ve bu sayı dünya genelindeki cihazlar için yetersizdir. Private IP adresleri sayesinde, binlerce cihazdan oluşan bir yerel ağ, internete çıkarken sadece tek bir public IP adresini paylaşabilir.

Ağ Güvenliği: Private IP adresleri internet üzerinde doğrudan erişilebilir değildir. Bu durum, dahili ağdaki cihazları dış dünyadan gelen doğrudan saldırılara ve bilgisayar korsanlarına karşı doğal bir bariyerle korur.

NAT (Network Address Translation) Kullanımı: Dahili ağdaki private IP'ler, NAT teknolojisi aracılığıyla public IP'lere dönüştürülerek internete erişir. NAT, iç adres yapısını gizleyerek bir güvenlik tamponu görevi görür.

Esnek Yönetim ve Segmentasyon: Kurumlar, dış dünyaya bağımlı kalmadan kendi iç ağlarını diledikleri gibi alt ağlara bölebilir ve yönetebilirler.

Localhost ve Loopback (127.0.0.1)

Loopback ağı, IPv4 protokolünde 127.0.0.0/8 bloğu olarak tanımlanmış özel bir ağdır.
Bu bloktaki en yaygın kullanılan adres 127.0.0.1 olup, standart alan adı localhost'tur.
Cihazların kendi üzerlerindeki ağ servislerini veya donanımlarını (örneğin Ethernet kartı) test etmeleri için kullanılır.
Bu adrese gönderilen trafik cihazın dışına çıkmaz, doğrudan cihazın kendisine geri döner. Yani loglarda 127.0.0.1 görülmesi, trafiğin dış ağdan değil bilgisayarın kendi içinden geldiğini gösterir. 



CIDR Gösterimi 

CIDR (Classless Inter-Domain Routing), IP adresinin kaç bitinin ağ kısmına ait olduğunu gösteren gösterim biçimidir. 
Örneğin, 192.168.1.10/24 ‘teki  /24, ilk 24 bitin ağ adresini, kalan 8 bitin ise cihaz (host) adresini temsil ettiğini ifade eder.
CIDR gösterimi, ağın büyüklüğünü ve kaç cihazı destekleyebileceğini belirlemek için kullanılır. 


Default Gateway(Varsayılan Ağ Geçidi)
Bir yerel ağdaki (LAN) cihazların farklı bir ağa veya internete erişmek istediklerinde kullandıkları çıkış kapısıdır.
Genellikle yerel ağda bulunan ve internete bağlı olan yönlendiricinin (router) IP adresidir.
Bir cihaz paket göndermek istediğinde, hedef IP'nin kendi ağında olmadığını alt ağ maskesiyle tespit ederse, paketi doğrudan varsayılan ağ geçidine yollar. Ağ geçidi, paketin başlık bilgilerine bakarak onu doğru hedef ağa yönlendirir.
  

Temel Formüller

Toplam IP Sayısı: 2^n (n: Host/cihaz için kalan bit sayısı).
Kullanılabilir Host Sayısı: 2^n-2 (Ağ ve Yayın adresleri çıkarılır).
Ağ Adresini Bulma: IP adresi ile Alt Ağ Maskesi arasında mantıksal VE (AND) işlemi yapılır.



Subnetting Alıştırmaları

Alıştırma1:

Soru: 192.168.41.0/26 ağında kaç adet kullanılabilir host (cihaz) adresi vardır?

Çözüm:

IPv4 toplam 32 bittir. CIDR notasyonundaki /26, ilk 26 bitin ağa ait olduğunu söyler. Geriye hostlar için 32−26=6 bit kalır.
Kullanılabilir host sayısı 2^6 - 2 formülü ile hesaplanır.
Sonuç: 64−2= 62 kullanılabilir host adresi vardır.


Alıştırma2:
Soru: 192.168.2.15 IP adresi 192.168.1.0/24 ağına ait midir?
Çözüm:
Ağ adresi 192.168.1 olmalıdır.
Verilen IP ise 192.168.2.15 olduğundan farklı bir ağdadır.
Sonuç: Hayır, bu IP farklı bir ağa aittir.

Alıştırma 3

Soru: 155.223.101.3 ve 155.223.101.15 adresleri, 255.255.255.0 maskesine sahip aynı ağda mı yer alıyor?

Çözüm (AND İşlemi):

Eğer iki IP'nin maske ile çarpımı (VE işlemi) aynı Ağ Adresini (Network ID) veriyorsa aynı ağdadırlar.
1. Cihaz: 155.223.101.3 VE 255.255.255.0 = 155.223.101.0.
2. Cihaz: 155.223.101.15 VE 255.255.255.0 = 155.223.101.0.
Sonuç: Her iki işlemin sonucu da aynı ağ adresini verdiği için bu iki bilgisayar aynı ağdadır.


DNS(Domain Name System)

DNS, alan adlarını IP adreslerine çeviren sistemdir. Böylece kullanıcılar IP adresi yerine www.google.com gibi alan adlarını kullanarak web sitelerine erişebilir. 

Çalışma Süreci:

Tarayıcıya bir isim yazıldığında, bilgisayar önce kendi önbelleğine veya hosts dosyasına bakar.
Eğer bilgi orada yoksa, internet servis sağlayıcısının (ISS) Yerel DNS Sunucusuna başvurur.
Yerel sunucu da yanıtı bilmiyorsa sırasıyla Kök (Root) Sunuculara, ardından .com veya .net gibi uzantılardan sorumlu TLD Sunucularına ve son olarak o sitenin gerçek kayıtlarını tutan Yetkili (Authoritative) Sunucuya gider.
Yetkili sunucu IP adresini döndürür; bu bilgi yerel sunucuda gelecekte kullanılmak üzere önbelleğe alınır ve kullanıcıya iletilir.


DHCP (Dynamic Host Configuration Protocol)
DHCP, ağa bağlanan cihazlara otomatik olarak IP adresi, subnet mask, default gateway ve DNS sunucusu gibi ağ bilgilerini atayan protokoldür.
DORA Süreci:

Discover (Keşif): Cihaz ağa bağlandığında "Burada bir DHCP sunucusu var mı?" diye tüm ağa bir yayın (broadcast) gönderir.
Offer (Teklif): DHCP sunucusu bu çağrıyı alır ve uygun bir IP adresini cihaza teklif eder.
Request (İstek): Cihaz teklifi kabul eder ve bu adresi kullanmak istediğini belirtir.
Acknowledge (Onay): Sunucu işlemi onaylar ve cihaz artık o IP ile ağa dahil olur.


NAT (Network Address Translation)
NAT, yerel ağdaki private IP adreslerini public IP adresine çevirerek cihazların internete çıkmasını sağlar. Böylece birden fazla cihaz tek bir public IP'yi paylaşabilir.
Nasıl Çalışır?
Bir iç cihaz dışarıya veri gönderdiğinde, yönlendirici (router) paketin üzerindeki yerel kaynak IP'yi siler ve yerine kendi genel IP'sini yazar.
PAT (NAT Overload): Evlerde en sık kullanılan yöntemdir. Yönlendirici, tek bir genel IP üzerinden gelen binlerce paketi birbirinden ayırmak için her bağlantıya benzersiz bir port numarası atar ve bunu bir "NAT Tablosunda" tutar. Yanıt geldiğinde tabloya bakarak paketi iç ağdaki doğru cihaza teslim eder.
NAT, iç ağdaki gerçek adresleri gizlediği için bir güvenlik tamponu görevi de görür.


ARP (Address Resolution Protocol)
ARP, yerel ağda bir IP adresine karşılık gelen MAC adresini bulmak için kullanılan protokoldür. Bir cihaz veri göndermeden önce hedef cihazın MAC adresini ARP ile öğrenir. 
Çalışma Süreci:

A cihazı, B cihazına paket göndermeden önce kendi ARP tablosuna bakar.
Eğer B'nin MAC adresi tabloda yoksa, A tüm ağa "Bu IP'ye (örneğin 192.168.1.5) kim sahip? Lütfen MAC adresini bana bildir!" diye bir broadcast mesajı yollar.
Sadece o IP'ye sahip olan cihaz bu isteğe kendi MAC adresini içeren bir unicast yanıtla cevap verir.
A cihazı bu MAC adresini öğrenir, tablosuna kaydeder ve veri iletişimini başlatır.


