**ENGEL-1** 

**SORUN:** 

sigma-cli kurulumundan sonra (pip3 install sigma-cli \--break-system-packages) sigma komutu terminalde tanınmadı. 

**HATA MESAJI:** 

"WARNING: The script sigma is installed in '/home/vboxuser/.local/bin' which is not on PATH. Consider adding this directory to PATH..." 

**DENENENLER:** 

sigma \--help doğrudan çalıştırıldı, "command not found" alındı. 

**ÇÖZÜM:** 

echo 'export PATH="HOME/.local/bin:HOME/.local/bin: HOME/.local/bin:PATH"'   
 \~/.bashrc ile PATH'e eklendi, source \~/.bashrc ile oturuma uygulandı.   
sigma \--help komutu sonrasında çalıştı. 

**KAYNAK:** 

pip3 install çıktısındaki WARNING satırı.

**ENGEL-2** 

**SORUN:** 

sigma plugin install splunk ve sigma plugin install sysmon komutları başarısız oldu. 

**HATA MESAJI:** 

"error: externally-managed-environment ... This environment is externally managed ... See PEP 668 for the detailed specification." ardından CalledProcessError ile pip \-m pip install pysigma-backend-splunk==2.1.0 komutunun başarısız döndüğü görüldü. 

**DENENENLER:** 

Komut olduğu gibi tekrar çalıştırıldı, aynı hata tekrarladı. sigma plugin install komutunun arka planda kendi pip çağrısını yaptığı ve \--break-system-packages bayrağını iletmediği anlaşıldı. 

**ÇÖZÜM:** 

export PIP\_BREAK\_SYSTEM\_PACKAGES=1 ortam değişkeni ayarlandı, bu sayede sigma-cli'nin arka planda çağırdığı pip komutları da sistem paketlerine yazma izniyle çalıştı. sigma plugin install splunk ve sigma plugin install sysmon bu ayardan sonra "Successfully installed plugin" mesajıyla tamamlandı. 

**KAYNAK:** 

Komut çıktısındaki hata metni ve traceback.

