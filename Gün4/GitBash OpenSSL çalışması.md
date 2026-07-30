Bu uygulamada OpenSSL kullanılarak message.txt dosyası AES-256-CBC algoritması ile bir parola kullanılarak şifrelendi ve message.enc dosyası oluşturuldu. Daha sonra aynı parola kullanılarak dosyanın şifresi çözüldü ve orijinal içerik message_solved.txt dosyasında başarıyla geri elde edildi.



gayen@GayeNurG▒ne▒ MINGW64 ~
$ echo "This is a test message" > message.txt

gayen@GayeNurG▒ne▒ MINGW64 ~
$ openssl enc -aes-256-cbc -salt -in message.txt -out message.enc
enter AES-256-CBC encryption password:

Verifying - enter AES-256-CBC encryption password:

*** WARNING : deprecated key derivation used.
Using -iter or -pbkdf2 would be better.

gayen@GayeNurG▒ne▒ MINGW64 ~
$ ^C

gayen@GayeNurG▒ne▒ MINGW64 ~
$ openssl enc -aes-256-cbc -pbkdf2 -salt -in message.txt -out message.enc
enter AES-256-CBC encryption password:

Verifying - enter AES-256-CBC encryption password:


gayen@GayeNurG▒ne▒ MINGW64 ~
$ openssl enc -d -aes-256-cbc -pbkdf2 -in message.enc -out message_solved.txt
enter AES-256-CBC decryption password:


gayen@GayeNurG▒ne▒ MINGW64 ~
$ cat message_solved.txt
This is a test message

