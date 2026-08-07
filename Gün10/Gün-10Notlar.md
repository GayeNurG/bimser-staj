Web Sitesine Bağlanma Süreci 

Bir web sitesine erişim sırasında ağ üzerinde birçok işlem sırasıyla gerçekleşir.

İlk olarak tarayıcıya yazılan alan adının hangi IP adresine karşılık geldiği DNS (Domain Name System) tarafından bulunur. Alan adı IP adresine çevrildikten sonra istemci doğru sunucuya ulaşabilir.

Veri gönderilmeden önce TCP/IP modeli katmanlarına göre kapsüllenir (encapsulation). Uygulama katmanında oluşturulan veri, taşıma katmanında TCP başlığı, internet katmanında IP başlığı ve ağ erişim katmanında çerçeve bilgileri eklenerek fiziksel ortama gönderilir.

İstemci ile sunucu arasında güvenilir bir bağlantı kurulabilmesi için TCP Three-Way Handshake gerçekleştirilir. Süreç sırasıyla SYN → SYN-ACK → ACK paketlerinden oluşur. Bu aşamanın tamamlanmasıyla iki cihaz arasında veri alışverişi başlayabilir.

Web siteleri HTTPS kullanıyorsa iletişim varsayılan olarak 443 numaralı TCP portu üzerinden gerçekleşir. TCP bağlantısı kurulduktan sonra TLS ile şifreleme sağlanır. Böylece ağ trafiği yakalanabilse bile paket içerikleri doğrudan okunamaz.

Paketler hedefe ulaşırken ağ cihazları farklı görevler üstlenir. Switch, aynı yerel ağ içerisinde MAC adreslerini kullanarak iletim yapar. Router, IP adreslerine göre paketleri farklı ağlara yönlendirir. Firewall ise güvenlik kurallarına göre hangi trafiğin geçeceğine veya engelleneceğine karar verir.

Sunucu isteği işledikten sonra oluşturduğu yanıtı aynı iletişim yolu üzerinden istemciye gönderir. Gelen paketler katmanlar tarafından açılır (decapsulation) ve tarayıcı web sayfasını kullanıcıya görüntüler.

DNS, TCP/IP modeli, kapsülleme, TCP Three-Way Handshake, HTTPS/TLS, portlar, switch, router ve firewall birbirinden bağımsız çalışan kavramlar değildir. Bir web sitesine erişim sürecinde her biri farklı bir görevi yerine getirerek iletişimin güvenli ve doğru şekilde gerçekleşmesini sağlar.

