# Feladat

A feladatok megoldásához az IDEA fejlesztőeszközt használd!
Bármely régebbi osztályt megnyithatsz.

Új repository-ba dolgozz!
Ezen könyvtár tartalmát nyugodtan át lehet másolni (`pom.xml`, tesztek).
Projekt, könyvtár, repository neve legyen: `sv2021-jvjbf-reszvizsga-pot`.
GroupId: `training360`, artifactId: `sv2021-jvjbf-reszvizsga-pot`. Csomagnév: `booking`.

Ha ezzel kész vagy, azonnal commitolj!

Először másold át magadhoz a teszteseteket, majd commitolj azonnal!

A feladatra 3 órád van összesen!

Ha letelik az idő mindenképp commitolj, akkor is
ha nem vagy kész!

**Érdemes közben is commitolni, mert az időn túl leadott megoldásokat nem tudjuk értékelni!**

**Az alkalmazásnak le kell fordulnia, ami le sem fordul, azt nem tudjuk értékelni!**

## Feladatleírás

A mai feladatban egy online szállásfoglaló rendszer alkalmazást kell megvalósítanod.

Az alap entitás az `Accommodation`, azaz a szállás. Minden szállásnak van egy azonosítója, egy neve, 
továbbá tudjuk, hogy melyik városában található. Rendelkezik még egy egész számokat tartalmazó listával, 
amiben a beérkezett foglalásokat tárolja (a vendégek számát foglalásonként), a max kapacitással, az aktuális 
szabad férőhelyek számával, illetve egy árral amit per fő kell érteni.

Amikor egy új foglalás érkezik figyeljünk arra hogy változzon az elérhető helyek száma, méghozzá 
ez a maximumkapacitás és a már beérkezett foglalások különbsége.  

A `BookingService` osztály tárolja egy listában a szállásokat. Kezdetben a lista üres. Ez az osztály felelős az egyedi azonosítók kiosztásáért is.

A `BookingController` osztály alapértelmezetten az `api/accommodations` URL-n várja a kéréseket és a következő funkciókat valósítja meg: 

* Le lehet kérdezni az összes szállást, és opcionálisan a városra is rá lehet szűrni. Ekkor mindig a nevet, az országot, 
  a várost, az elérhető helyek számát és az árat adjuk vissza. 
* A `/{id}` URL-n lehessen egy aktuális szállást lekérni. (név, ország, város, szabad helyek, ár)  
* Az alapértelmezett URL-n lehessen új szállást felvenni. Ekkor a nevet a várost,  a max kapacitást és az árat adjuk meg. 
* Lehessen egy szállás árát frissíteni. (Ha ugyanarra az árra akarjuk változtatni akkor ne történjen fölösleges metódushívás)
* Lehessen egy szállásra foglalni a `/{id}/book` URL-n kersztül. Ekkor egy számot várunk a törzsben, hogy mennyi szabad ágyra lenne szükség. 
* Lehessen törölni az összes szállást
* A következő szempontokat vegyük még figyelembe:
	* Új szállás neve és városa nem lehet üres, és legalább 10 helyet kell biztosítani
	* Ha a megfelelő id-n keresztül nem található a film akkor 404-es státuszkóddal térjünk vissza.
	* Ha több helyet akarunk foglalni mint ahány szabad hely van, akkor ne történjen meg a foglalás. Térjünk vissza 400 BAD_REQUEST státuszkóddal 
