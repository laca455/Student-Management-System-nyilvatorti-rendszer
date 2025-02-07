# Student-Management-System-nyilvatorti-rendszer

==== Diák nyílvántartási rendszer ====

A projekten van még mit feljeszteni, ez csak egy fél kész de futtatható. H2 adatbázissal. 
Diákok nyilvántartása (Név,email,születési idő,ID,Index)

Jellemzőkek a projektre (mini részlet)

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
