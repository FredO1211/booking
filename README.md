Booking app — aplikacja do zarządzania rezerwacjami hotelowymi. 

Usługa pozwalająca zarządzać rezerwacjami hotelowymi oparta o system
CRUDowy. System za pomocą udostępnionego API
RESTowego daje możliwość dodania nowych miejsc noclegowych,
rezerwacji, gości oraz płatności, jak również ich odczytu i modyfikacji. 
Za pomocą odpowiednich endpointów możemy uzyskać informację o 
płatnościach, które nie zostały jeszcze dokonane, otrzymać listę 
rezerwacji w uproszczonym formacie, jak również wysłać wiadomość do 
wszystkich gości zarejestrowanych w bazie.

Aplikacja wspiera automatyczne asynchroniczne wysyłanie potwierdzeń do
gości.

Wykorzystane technologie:
Java 11, SpringJpa, BazaDanych H2, FlyWay,
Lombok, RabbitMq, HATEOAS, Junit5, Mockito, Swagger2

Dokumentacja:
Graficzny interfejs: *host*/swagger-ui/index.html 
Format JSON: *host*/v2/api-docs


