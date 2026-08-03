ENGEL-1

SORUN:

Oracle VirtualBox 7.2.14 uygulamasının kurulumu sırasında programı yüklemeye çalışırken kurulum tamamlanamadı. Kurulumun devam edebilmesi için ek bir paketin gerekli olduğu uyarısını aldım. 

HATA MESAJI:

“Oracle VirtualBox 7.2.14 uygulamasının öncelikle Microsoft Visual C++ 2019 Yeniden Dağıtılabilir Paketinin yüklenmesine ihtiyacı var. Lütfen Oracle VirtualBox 7.2.14 uygulamasının kurulumunu yeniden başlatın ve yükleyin.”

DENENENLER:

İlk olarak VirtualBox kurulumunu tekrar çalıştırdım ancak aynı uyarıyı aldım. 
Hata mesajını araştırdım. 
Microsoft Visual C++ Redistributable paketini yükledim. 
Daha sonra VirtualBox kurulumunu yeniden başlattım. 

KAYNAK:

Microsoft Visual C++ Redistributable download page


ENGEL-2

SORUN:   

Ubuntu virtual machine oluşturulduktan sonra kurulum ve sistem açılışı beklediğimden yavaş ilerledi. 

HATA MESAJI:

Hata mesajı almadım. Performansın düşük olmasından dolayı bu soruna çözüm aradım.

DENENENLER:

Sanal makineye başlangıçta 2048 MB RAM, 1 işlemci ve 25 GB disk alanı ayırdım. 
RAM ve işlemci ayarlarını değiştirdim. 

ÇÖZÜM:

RAM’i 4096 MB'a, işlemci sayısını ise 2'ye çıkardım. Bu değişiklikten sonra sanal makinenin daha akıcı çalışması sağlandı. 

KAYNAK:

Oracle VirtualBox ayarları 

