# EPIDOGSITTING RESTAPI

EpiDogSitting RestAPI basato su Java Spring-Boot

# PRIMA DI INIZIARE

Configurare il file application.properties con i relativi dati personalizzati (dati di connessione al DB)

# ENDPOINTS

Gli endpoint sono tutti documentati tramite OpenAPI e Swagger.

Possono essere consultati in formato JSON tramite una richiesta GET a https://nomehost:porta/api/docs

oppure

consultando https://nomehost:porta/api/swagger-ui.html da browser web.

# AUTHENTICATION

Tutti gli endpoint sono protetti da Autenticazione JWT Based, e Ruoli (DogSitter e DogOwner).

# EPIDOGSITTING INTERFACE

E' disponibile un'interfaccia Front-End, progettata in concomitanza con questo API in occasione del Capstone Project di EPICODE.

Consulta il mio Git --> https://github.com/phyxius23/EpiDogSitting-FrontEnd
