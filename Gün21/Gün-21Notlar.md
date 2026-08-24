NIST Olay Müdahale Yaşam Döngüsü (NIST SP 800-61)

Bu döngü, bir güvenlik olayının tespit edilmesinden kapatılmasına kadar geçen süreci dört aşamaya ayırır. Önemli nokta: bu aşamalar birbirini takip eden ama birbirinden kopuk kutular değil. Bir olay sırasında sık sık geri dönülür. Döngü "önce şunu bitir, sonra buna geç" değil, birbirini besleyen bir akış.

1. Hazırlık (Preparation)
Bir olay daha gerçekleşmeden önce yapılan tüm hazırlıktır. Log toplama altyapısının kurulu olması, SIEM'in çalışıyor olması, hangi log kaynağının nerede tutulduğunun bilinmesi, playbook'ların (belirli olay tiplerinde ne yapılacağının önceden yazılmış olması) var olması, ekibin yetkilerinin ve erişimlerinin tanımlı olması. Bu aşama görünürde "olay yokken" gerçekleşir ama olayın çözülebilirliğini belirleyen asıl aşamadır.
Case 1'de karşılığı: web01'in izleme sisteminin zaten kurulu olması, auth.log'un Splunk'a index=main / sourcetype=linux_secure altında akıyor olması. Ben rapora başlarken bu veriye zaten erişebiliyordum, bu hazır olduğu için mümkündü. Ayrıca SPL sorgusu yazabiliyor olmam da aslında kişisel bir "hazırlık", bilgi/beceri hazırlığı.
Neden önemli?: Hazırlık zayıfsa iki türlü sorun çıkar: ya log hiç tutulmamıştır (o zaman olay görünmez), ya da log tutulur ama analiz edecek kimse/yöntem yoktur. Case 1'de her ikisi de vardı. Hazırlık yoksa geri kalan üç aşamanın hiçbiri mümkün olmaz.

2. Tespit ve Analiz (Detection & Analysis)
Bir şeyin normalden saptığının fark edilmesi (tespit) ve bu sapmanın ne olduğunun, nasıl olduğunun, kim tarafından yapıldığının kanıtlarla ortaya konması (analiz). Burada iş sadece "alarm çaldı" değil; ham veriden bir olay hikâyesi kurmaktır.
Case 1'de karşılığı: İzleme sisteminin kısa sürede çok sayıda başarısız SSH girişimini fark etmesi tespit kısmıydı. Analiz kısmı ise attığım adımlar: önce kaynak IP'nin ve deneme sayısının çıkarılması(Bulgu 1), sonra hangi kullanıcı adlarının denendiği bilgisi(Bulgu 2), başarılı girişin tespiti(Bulgu 3), bu girişin hesabın normal davranışıyla karşılaştırılıp anormalliğin kanıtlanması (Bulgu 4), son olarak giriş sonrası komutlarının (shadow okuma, useradd) tespiti(Bulgu 5). Bu beş bulgu, tek başına "91 hata satırı" olan ham veriyi bir saldırı anlatısına dönüştürdü.
Neden önemli?: Tespit olmadan olay hiç fark edilmez; analiz olmadan ise tespit edilen şeyin ciddiyeti, kapsamı ve kim/ne olduğu bilinmez. Dolayısıyla ne yapılması gerektiğine karar verilemez. Raporumdaki zaman çizelgesi bu aşamanın çıktısıdır.

3. Sınırlama, Yok Etme, Kurtarma (Containment, Eradication, Recovery)
Bu aşamanın üç alt adımı bulunuyor:
Containment (Sınırlama): Olayın daha fazla yayılmasını/zarar vermesini durdurma. Acil ve genelde geçici bir müdahale.
Eradication (Yok Etme): Saldırganın sistemde bıraktığı her şeyi (arka kapı, kötü amaçlı hesap, script) kalıcı olarak temizleme.
Recovery (Kurtarma): Sistemi güvenli ve normal çalışır duruma döndürme, gerekirse sertleştirme (hardening) yaparak.
Case 1'de karşılığı:
Containment: deploy hesabının parolasının değiştirilmesi, aktif oturumların sonlandırılması, saldırgan IP'nin (203.0.113.66) güvenlik duvarında engellenmesi.
Eradication: saldırganın oluşturduğu svc-update hesabının kaldırılması.
Recovery: SSH'ta parola tabanlı kimlik doğrulamanın kaldırılıp anahtar (key) tabanlı girişe geçilmesi, erişimin yalnızca iç ağ/VPN üzerinden yapılacak şekilde kısıtlanması.
Neden önemli?: Bu aşama olmadan tespit ve analiz sadece "biliyoruz ama hiçbir şey yapmıyoruz" durumunda kalır. Özellikle eradication atlanırsa (svc-update silinmezse), deploy hesabı kapatılsa bile saldırgan ikinci bir kapıdan geri dönebilir.

4. Olay Sonrası (Post-Incident)
Olay kapandıktan sonra yapılan değerlendirme: ne öğrenildi, hangi kontrol eksikti, bir dahaki sefere aynı şey nasıl daha erken/daha az hasarla yakalanır.
Case 1'de karşılığı: Raporumun "Öneriler" ve "Sonuç" bölümleri bu aşamanın ürünü. Özellikle şu tespitler post-incident düşüncesinin tipik örnekleri: 91 başarısız denemeye kadar hiçbir otomatik engelleme mekanizmasının devreye girmemiş olması, /etc/shadow gibi kritik bir dosyaya erişim olduğunda uyarı üretecek bir izlemenin olmaması, ve SSH'ın normalde sadece iç ağdan erişilmesi gerekirken dışa açık olmasının kök neden olarak belirlenmesi.
Neden önemli?: Bu aşama, olayı tek seferlik olmaktan çıkarıp sistemi bir sonraki olaya karşı gerçekten güçlendiren aşamadır. Containment/eradication olayı kapatır, post-incident ise "bu neden mümkün oldu, bir daha olmasın" sorusuna cevap verir.

