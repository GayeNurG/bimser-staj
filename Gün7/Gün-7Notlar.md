# OSI Model Katmanları

OSI (Open Systems Interconnection) modeli, bilgisayar ağlarında iletişimin nasıl gerçekleştiğini tanımlar. Bu modelin temel amacı, farklı üreticilerin donanım ve yazılımlarının ortak bir standart üzerinden birbiriyle sorunsuz iletişim kurmasını sağlamaktır.

## Physical Layer (Fiziksel Katman)

Verilerin fiziksel ortam (kablo, fiber optik, radyo sinyalleri) üzerinden bit olarak taşınmasını tanımlar. Elektriksel, mekanik ve fonksiyonel özellikleri belirler.

**Örnekler:** Hub, ağ kartı (NIC), kablolar, tekrarlayıcılar.

## Data Link Layer (Veri Bağlantısı Katmanı)

Fiziksel katmana erişim kurallarını belirler ve uç düğümler arasında güvenli veri transferi sağlar. Hata kontrolü ve çerçeveleme işlemlerini yapar. İki alt katmana ayrılır: MAC (media access control, donanım adresleme) ve LLC (logical link control, akış kontrolü ve hata tespiti).

**Örnekler:** Ethernet, Wi-Fi, Switch, Bridge.

## Network Layer (Ağ Katmanı)

Farklı ağlar arasında veri paketlerinin yönlendirilmesini ve mantıksal adreslemeyi (IP) sağlar. Verinin hedefe ulaşması için en ekonomik yolu seçer.

**Örnekler:** IP (IPv4, IPv6), ICMP, ARP, Router.

## Transport Layer (Taşıma Katmanı)

Verinin uçtan uca güvenli, hatasız ve doğru sıralı iletiminden sorumludur. Büyük verileri segmentlere ayırır ve alıcıda tekrar birleştirir.

**Örnekler:** TCP (bağlantı temelli), UDP (bağlantısız).

## Session Layer (Oturum Katmanı)

İki bilgisayar arasındaki uygulamaların bağlantısını kurar, yönetir ve sonlandırır. Haberleşmenin organize edilmesini ve senkronizasyonunu sağlar.

**Örnekler:** NetBIOS, RPC, SMB, NFS, SQL.

## Presentation Layer (Sunum Katmanı)

Verilerin uygulama katmanına sunulmadan önce formatlanması, sıkıştırılması ve şifrelenmesi işlemlerini yapar. Farklı sistemlerin veriyi aynı şekilde anlamasını sağlar (örneğin ASCII'den EBCDIC'ye dönüşüm).

**Örnekler:** JPEG, MPEG, GIF, SSL/TLS, TIFF, ASCII.

## Application Layer (Uygulama Katmanı)

Kullanıcıya en yakın katmandır; ağ servislerini kullanacak olan programlarla (tarayıcılar, e-posta istemcileri vb.) doğrudan etkileşim kurar.

**Örnekler:** HTTP, HTTPS, FTP, SMTP, DNS, SSH, Telnet.

## Encapsulation (Kapsülleme)

Ağ iletişiminde bir bilgisayardan diğerine veri gönderilirken, verinin üst katmanlardan alt katmanlara doğru ilerlemesi ve her bir katmanda veriye o katmana özgü belirli bilgilerin eklenmesi işlemidir.

# TCP/IP Katmanları

## Uygulama Katmanı (Application Layer)

Bu katman, OSI modelindeki Uygulama, Sunum ve Oturum katmanlarının görevlerini tek başına üstlenir. Bilgisayarda çalışan uygulama yazılımlarına hizmet sunar ve düğümden düğüme uygulama iletişimini sağlar. Kullanıcı ile ağ arasındaki arabirimi oluşturur.

**Örnekler:** HTTP, FTP, SMTP, DNS, Telnet, SSH.

## Taşıma Katmanı (Transport Layer / Host-to-Host)

Uç birimler arasındaki iletişimin güvenilirliğini yönetir. Verilerin hatasız tesliminden, akış kontrolünden ve büyük veri parçalarının segmentlere ayrılmasından sorumludur.

**Örnekler:** TCP (Bağlantılı/Güvenilir) ve UDP (Bağlantısız/Hızlı).

## İnternet Katmanı (Internet Layer)

Paketlerin farklı ağlar arasında mantıksal iletimini ve yönlendirilmesini sağlar. Paketlerin kaynaktan hedefe en uygun yolla gitmesinden sorumludur.

**Örnekler:** IP (IPv4, IPv6), ICMP, ARP.

## Ağ Erişim Katmanı (Network Access Layer / Link)

Bu katman, OSI modelindeki Fiziksel ve Veri Bağlantısı katmanlarını birleştirir. Verinin fiziksel ağ (kablo, fiber, radyo sinyalleri) üzerinden iletilmesi için gerekli donanım ve protokolleri tanımlar. Cihaz ile ağ arasında paketleri gönderecek bağlantının kurulmasını sağlar.

**Örnekler:** Ethernet, Wi-Fi, Token Ring.

# OSI ve TCP/IP Arasındaki Temel Farklar

OSI 7 katmanlıyken, TCP/IP genellikle 4 katmanlıdır. OSI eğitimi amaçlı bütünsel bir bakış açısı sunan bir referans modelidir; TCP/IP ise mevcut protokollerin tanımlanmasıyla ortaya çıkan uygulanmış bir modeldir. OSI'de her katman kesin kurallarla tanımlanmıştır, TCP/IP daha esnektir ve bazı protokoller katmanlar arasında geçiş yapabilir.

# Verinin İletim Aşamaları

1. **Uygulama Katmanı:** Kullanıcı tarayıcıya bir site yazar veya bir mesaj gönderir. Oluşan bilgiye veri (data) denir.
2. **Taşıma Katmanı (TCP/UDP):** Veriye bir TCP veya UDP başlığı eklenir. Bu başlıkta hangi uygulamaya gideceği (port numarası), sıralama ve hata kontrolü gibi bilgiler bulunur.
3. **Ağ Katmanı (IP):** Pakete IP başlığı eklenir. Bu başlık sayesinde paketin kaynak IP ve hedef IP adresleri belirlenir, yani internette nereye gideceği anlaşılır.
4. **Veri Bağlantı Katmanı (Ethernet):** IP paketine MAC adreslerini içeren Ethernet başlığı ve hata kontrolü için FCS (trailer) eklenir. Böylece paket yerel ağda iletilmeye hazır olur.
5. **Fiziksel Katman:** Paket artık elektrik sinyali, ışık sinyali veya radyo dalgası şeklinde kablo ya da Wi-Fi üzerinden gönderilir.

Sunucuya ulaştığında, sunucu işlemleri ters sırayla yapar. Gönderirken her katman veriye kendi başlığını ekler (kapsülleme / encapsulation), alıcıda ise her katman kendi başlığını çıkarır (kapsül açma / de-encapsulation). Böylece veri güvenli ve doğru hedefe ulaşmış olur.

# TCP'nin Üçlü El Sıkışması (Three-Way Handshake)

TCP (Transmission Control Protocol) bağlantı odaklı bir protokoldür. Bu nedenle veri gönderilmeden önce istemci (client) ile sunucu (server) arasında güvenilir bir bağlantı kurulması gerekir. Bu bağlantının kurulma sürecine Three-Way Handshake (Üçlü El Sıkışma) denir.

**Three-Way Handshake'in amacı:**
- İstemci ve sunucunun haberleşmeye hazır olduğunu doğrulamak.
- Başlangıç sıra numaralarını (Initial Sequence Number, ISN) senkronize etmek.
- Güvenilir bir TCP bağlantısı oluşturmak.

## Önemli Kontrol Bitleri

| Bit | Anlamı |
|---|---|
| SYN (Synchronize) | Sıra numaralarını senkronize etmek ve bağlantı başlatmak için kullanılır |
| ACK (Acknowledgment) | Alınan paketin onaylandığını belirtir |
| FIN (Finish) | Göndericinin artık veri göndermeyeceğini belirtir |
| RST (Reset) | Bağlantıyı sıfırlar |
| PSH (Push) | Verinin bekletilmeden uygulamaya iletilmesini ister |
| URG (Urgent) | Acil veri bulunduğunu belirtir |

## Bağlantı Kurulma Aşamaları

1. **SYN (İstemci → Sunucu):** İstemci bağlantı kurmak istediğini belirtmek için SYN paketi gönderir. Bu pakette başlangıç sıra numarası (ISN) bulunur, ACK değeri 0'dır çünkü henüz karşı taraftan bir paket alınmamıştır. İsteğe bağlı olarak MSS (Maximum Segment Size) bilgisi de gönderilebilir; MSS, gönderen tarafın almak istediği maksimum TCP veri boyutunu belirtir.
2. **SYN-ACK (Sunucu → İstemci):** Sunucu SYN paketini aldıktan sonra istemcinin isteğini ACK ile onaylar, aynı zamanda kendi SYN paketini göndererek kendi sıra numarasını bildirir.
3. **ACK (İstemci → Sunucu):** İstemci sunucunun SYN-ACK paketini aldıktan sonra son olarak ACK paketi gönderir. Bu ACK ile sunucunun sıra numarası onaylanır, TCP bağlantısı ESTABLISHED durumuna geçer, artık uygulama verileri gönderilmeye başlanabilir.

## Port Nedir?

Port, bir bilgisayarda çalışan uygulamaların birbirinden ayırt edilmesini sağlayan mantıksal iletişim noktasıdır. Bir IP adresi hangi bilgisayara gidileceğini gösterirken, port numarası o bilgisayardaki hangi uygulamaya gidileceğini gösterir.

## TCP ve UDP Farkları

| | TCP | UDP |
|---|---|---|
| Bağlantı | Bağlantı kurar | Bağlantı kurmaz |
| Teslimat kontrolü | Verinin ulaşıp ulaşmadığını kontrol eder | Kontrol etmez |
| Kayıp paket | Kayıp paketleri tekrar gönderir | Tekrar göndermez |
| Sıralama | Paket sırasını korur | Bu sırayı garanti etmez |
| Hız | Daha yavaş | Daha hızlı |
