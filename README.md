# ITunes

![Logo](./logo.svg)

- TR ve EN olmak üzere 2 dil desteği eklendi.
- Theme Builder ile Material 3 gündüz & gece temaları oluşturuldu.
- Usecase’ler için JUnit5 kullanılarak unit test yazıldı.
- Coroutine ve flow kullanıldı.
- Dependency Injection için Hilt kullanıldı.
- OkHttp Logging ve Chucker ile debugging kolaylaştırıldı.
- Retrofit Call Adapter oluşturuldu.
- Date çevirileri için Room üzerinde Type Adapter ve Gson üzerinde Deserializer yazıldı.
- Resim yükleme için Landscapist kullanıldı.
- İlk Sayfada loading ve hata durumları Pager ile handle edildi. Hata durumlarında retry edilebilmesi sağlandı.
- Diğer sayfalarda loading için progress dialog ve hata durumlarında alert dialog kullanıldı. Popuplara retry seçeneği eklendi.
- Esnek ortak UI componentlar yazıldı.
