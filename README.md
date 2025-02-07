# Student-Management-System-nyilvatorti-rendszer
(A rendszer egy barátommal szerettük volna előállitani elüször angolnyelvre majd magyar nyelvre.)
(A program tartalmazhat angolt, de igyekeztünk ezt kiküszöbölni)
==== Diák nyílvántartási rendszer ====

A projekten van még mit feljeszteni, ez csak egy szinte fél kész de futtatható. H2 adatbázissal. Sprngbootot tartalmaz mivel egyszerősitett
illetve egy felhasználó barátabbat projectet szerettünk volna létrehozni. 
Diákok nyilvántartása (Név,email,születési idő,ID,Index)

Jellemzőkek a projektre (mini részlet)

# Ezeket az  adabázisokat tartalmazza:
-schema.sql
-data.sql

# Lehetőségeg:
- Mentés
- Összes megjelenítése
- Keresés azonosító alapján
- E-mail alapján keresés
- Keresés indexszám alapján
- Keresés két születési dátum között
- Frissítés azonosító alapján
- Törlés azonosító alapján
- Törlés e-mailben

Jellemzők:
- IDE
- Spring
- Spring  inicializáló
- H2 Beágyazott adatbázis
(A spring lehetőséget jobbnak találtuk a program elkészitéséhez  mivel, rendelkezik beépitett adatbzis tűmogatással,
kicsit egyszerűbb, könnyebb és elérhetőbb volt a számunkra, szerettünk volna egy könyebb felhasználóbarátabb programot )


GET METHOD PÉLDÁK és leírások
/all
Az összes diák listázása a DB-ből.

/find/{id}
Diák keresése a DB-ben az azonosító alapján.

/email/{email}
Diák keresése a DB-ben e-mail cím alapján.

/index/{index}
Diák keresése a DB-ben index alapján.

/születési dátum
Diákok keresése (lista) két születési dátum között.


Diák hozzá adása
/add
Add student with this in body: <br/>
{ <br/>
    "firstName": "Teszt", <br/>
    "lastName": "tESZT", <br/>
    "dateOfBirth": "2000-01-01", <br/>
    "email": "teszt@gmail.com", <br/>
    "indexNumber": 169, <br/>
    "isOnBudget": true / false <br/>
}

-   /delete-with-email/{email}
    Tanuló törlése e-mailben.

-   /delete/{id}
    Diák törlése az ID-vel.

-    /update/{id}
    Diák frissítése az id-vel.


Papp Lászlp NYE D51P4L
