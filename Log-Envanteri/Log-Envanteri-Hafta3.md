# Log Envanteri

| **Makine** | **Log Kaynağı** | **Konum** | **Kaydettiği Olaylar** | **Okuma Aracı** |
|---|---|---|---|---|
| Ubuntu | Authentication Log | `/var/log/auth.log` | SSH girişleri, başarılı/başarısız kimlik doğrulama ve `sudo` işlemleri | `grep`, `tail`, `head`, `less`, `journalctl` |
| Windows | Security | Event Viewer → Windows Logs → Security | Başarılı/başarısız oturum açma ve güvenlik olayları | Event Viewer, PowerShell |
| Windows | System | Event Viewer → Windows Logs → System | Sistem, servis ve sürücü olayları | Event Viewer, PowerShell |
| Windows | Application | Event Viewer → Windows Logs → Application | Uygulamaların oluşturduğu hata, uyarı ve çalışma olayları | Event Viewer, PowerShell |
| Windows | Sysmon | Event Viewer → Applications and Services Logs → Microsoft → Windows → Sysmon → Operational | Süreç, ağ bağlantısı, dosya oluşturma ve DNS sorgularını ayrıntılı kaydeder | Event Viewer, PowerShell |
