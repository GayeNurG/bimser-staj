ENGEL-1
SORUN:
Ubuntu sanal makinesi kullanılırken sistem tarafından "Sorry, Ubuntu has experienced an internal error." uyarısı görüntülendi.
HATA MESAJI:
"Sorry, Ubuntu has experienced an internal error."
DENENENLER:
Hata mesajının çalışmaları etkileyip etkilemediği kontrol edildi.
Sistemin genel çalışması gözlemlendi.
Çalışmaların normal şekilde devam ettiği görüldüğü için ek bir müdahalede bulunulmadı.
ÇÖZÜM:
Bu hata, yapılan çalışmaları etkilemediği için bu aşamada çözülmedi. Tekrar etmesi veya sistem performansını etkilemesi durumunda araştırılması planlanmaktadır.
KAYNAK:
Ubuntu hata raporu.

ENGEL-2
SORUN:
Ubuntu sanal makinesinden Windows sanal makinesine ping gönderildiğinde yanıt alınamadı (%100 packet loss). Buna karşın Windows'tan Ubuntu'ya gönderilen ping istekleri başarılı oldu.
HATA MESAJI:
Ubuntu terminalinde:
106 packets transmitted, 0 received, 100% packet loss
DENENENLER:
Her iki sanal makinenin IP adresleri kontrol edildi.
VirtualBox ağ yapılandırmaları doğrulandı.
Ping testi her iki yönde tekrarlandı.
Sorunun Windows Güvenlik Duvarı'ndan kaynaklanabileceği değerlendirildi.
ÇÖZÜM:
Windows Defender Güvenlik Duvarı'nda File and Printer Sharing (Echo Request - ICMPv4-In) kuralı etkinleştirildi. Kural etkinleştirildikten sonra Ubuntu ve Windows arasında iki yönlü ping iletişimi başarıyla sağlandı.
KAYNAK:
Windows Defender Firewall ayarları.


