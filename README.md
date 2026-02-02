# Logica aplicației
* În funcția main se verifică cazul (2 sau 4 parametri), iar logica de analiză și parsare a liniilor 
se face în metodele claselor ce moștenesc FileHandler.
* Clasa abstractă FileHandler conține logica de citire a liniilor, lăsând implementările specifice de parsare de comenzi claselor copii.
* Mi-am luat libertatea să modific Set-urile din Database în Map-uri, pentru a putea accesa în O(1) elementele (în loc de O(log(n)), dacă foloseam un TreeSet).

# Design patterns
## Singleton
Database-ul este un singleton, ceea ce permite obținerea ușoară în orice moment a bazei de date, fără a o adăuga ca parametru al clasei sau al metodelor.
Îmbunătățește și robustitatea aplicației, fiind imposibil să se creeze din greșeală 2 instanțe.
## Builder
Există 3 clase ce implementează Builder Pattern: 
* Server și Location motivat de prezența unui număr mare de parametri opționali în ambele cazuri 
* ResourceGroup, care are Server ca parametru doar când suntem în cazul cu 3 fișiere (este necesar pentru Observer-ul care propagă alertele).

Builder este preferat peste a folosi pur și simplu setteri
pentru că obiectele devin imutabile după creație, deci elimină riscul unei modificări accidentale.
## Factory
Există 3 Factory-uri:
* FileHandlerFactory care creează fișierele de output în funcție de caz. Este util pentru că nu mai este necesar să
tratăm fiecare tip separat la creare, alegerea făcându-se direct pe baza primului argument din linia de comandă.
* ServerUserFactory creează un user cu rolul corect dintre (User, Operator, Admin) când citirea se face dintr-un fișier de tip servers.
* GroupUserFactory la fel ca ServerUserFactory, dar pentru fișiere de tip groups. Existența separată de ServerUserFactory
este justificată de faptul că diferă cheile pentru aceiași parametri, și în acest caz ei trebuie adăugați și în lista
ResourceGroup-ului.
## Observer
Sunt implementați 2 observeri în "cascadă":
* ResourceGroup este un observer pentru Serverul cu același IP.
* Userii din ResourceGroup sunt observers pentru ResourceGroup.

Când apare o alertă Serverul notifică ResourceGroup-ul, care notifică toți membrii.
