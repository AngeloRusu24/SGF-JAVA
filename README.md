# SGF - Sistema Gestione Forniture

## Class Diagram UML

```mermaid
classDiagram
    direction TB

    class Utente {
        -Long id
        -String username
        -String passwordHash
        -RuoloEnum ruolo
        +login() boolean
        +logout() void
        +hasPermesso(String) boolean
    }

    class Fornitore {
        -Long id
        -String ragioneSociale
        -String pIva
        -String email
        -String telefono
        -String indirizzo
        +contatta() void
        +getProdotti() List~Prodotto~
    }

    class Prodotto {
        -Long id
        -String codice
        -String nome
        -String descrizione
        -Double prezzoUnitario
        -Integer scorta
        -Integer sogliaMinimaRiordino
        -Categoria categoria
        +isDisponibile() boolean
        +necessitaRiordino() boolean
    }

    class Categoria {
        -Long id
        -String nome
        -String descrizione
        +getProdotti() List~Prodotto~
    }

    class Ordine {
        -Long id
        -LocalDate dataOrdine
        -LocalDate dataConsegna
        -StatoOrdineEnum stato
        -Double totale
        -Utente creatore
        -Fornitore fornitore
        +conferma() void
        +annulla() void
        +calcTotale() Double
        +getRighe() List~RigaOrdine~
    }

    class RigaOrdine {
        -Long id
        -Integer quantita
        -Double prezzoUnitario
        -Prodotto prodotto
        +getSubtotale() Double
    }

    class Magazzino {
        -Long id
        -String nome
        -String ubicazione
        -String responsabile
        +getGiacenza() Map~Prodotto, Integer~
        +aggiornaStock(Prodotto, int) void
        +getProdottiSottoSoglia() List~Prodotto~
    }

    class MovimentoMagazzino {
        -Long id
        -TipoMovimentoEnum tipo
        -Integer quantita
        -LocalDateTime dataOra
        -String note
        -Prodotto prodotto
        -Magazzino magazzino
        -Utente operatore
        +registra() void
    }

    %% Enumerazioni
    class RuoloEnum {
        <<enumeration>>
        ADMIN
        MANAGER
        MAGAZZINIERE
    }

    class StatoOrdineEnum {
        <<enumeration>>
        BOZZA
        CONFERMATO
        SPEDITO
        CONSEGNATO
        ANNULLATO
    }

    class TipoMovimentoEnum {
        <<enumeration>>
        CARICO
        SCARICO
        RETTIFICA
    }

    %% Relazioni
    Utente --> RuoloEnum : ha
    Ordine --> StatoOrdineEnum : ha
    MovimentoMagazzino --> TipoMovimentoEnum : ha

    Utente "1" --> "*" Ordine : crea
    Fornitore "1" --> "*" Prodotto : fornisce
    Prodotto "*" --> "1" Categoria : appartiene a
    Ordine "1" *-- "*" RigaOrdine : composto da
    RigaOrdine "*" --> "1" Prodotto : riferisce
    Ordine "*" --> "1" Fornitore : indirizzato a
    Magazzino "1" o-- "*" MovimentoMagazzino : registra
    MovimentoMagazzino "*" --> "1" Prodotto : riguarda
    MovimentoMagazzino "*" --> "1" Utente : eseguito da
```

## Stack Tecnologico

| Layer | Tecnologia |
|---|---|
| Frontend | Angular 17+ + Angular Material |
| Backend | Java 17 + Spring Boot 3 |
| Persistenza | JPA/Hibernate + MySQL |
| Build | Maven (backend) + npm (frontend) |
| Test | JUnit 5 + Mockito |
| Documentazione API | Swagger / OpenAPI |

## Struttura del progetto

```
sgf/
├── backend/                  # Spring Boot
│   ├── src/main/java/
│   │   └── com/sgf/
│   │       ├── controller/
│   │       ├── service/
│   │       ├── repository/
│   │       ├── model/
│   │       ├── dto/
│   │       └── exception/
│   └── pom.xml
└── frontend/                 # Angular
    ├── src/app/
    │   ├── core/
    │   ├── features/
    │   └── shared/
    └── package.json
```
