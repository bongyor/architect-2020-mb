# Bank (JPA házi feladat 1.0) alkalmazás kotlinban

Kíváncsi voltam, hogy csupa kotlin-os eszközzel mennyiben lesz más a házi feladat, ez lett az eredmény.

Fontos, hogy ezek nem kipróbált technológiák, egy részük "experimental" státuszban van, illetve némelyikkel most találkoztam először, így nem biztos hogy mindent úgy használok, ahogy azt praktikus.

## Előnyök
* 8-as java is elég neki (mivel bank vagyunk, a java frissítés nálunk olyan mint spanyolcsizmában kocogni: lehet de inkább csinálja más...)
* shadow jar
    * 18M összesen
    * nem kell alkalmazásszerver, konfiguráció, stb.
* keretrendszer szinten támogatott autoreload
* könnyű tesztelhetőség

## Ismert hibák
* az autoreload csak ideából, az Application-t indítva működik
* adatbázis elérés be van égetve az `InitDb.kt`-ban

## Előfeltételek
* futtatáshoz java 8
* kényelmes fejlesztéshez a következő idea pluginok:
    * gradle
    * kotlin
    * ktor

## Működés
### Tesztek
* h2 adatbázis használata
* minden futásnál új db-t hoz létre
* minden teszt adatot a teszt tölt be a db-be
* ilyenkor web szerver nem indul, a keretrendszer leírásában talált módon tesztelek
### "Éles"
* futtatás lehetséges:
    * shadow jar `java -jar build/libs/bank-kt-0.0.1-all.jar`
    * idea-ban az `Application.kt` fájlban a zöld háromszögre kattintva
    * gradle segítségével
* postgre db-hez csatlakozik
* ha hiányzik tábla vagy mező, azt létrehozza
* funkcióban (reményeim szerint) megegyezik a bank alkalmazással

## Hasznos linkek
* Kotlin nyelv: https://kotlinlang.org/
* KTor keretrendszer: https://ktor.io/
* Perzisztens réteg: https://github.com/JetBrains/Exposed/wiki/DAO
* Dependency injection megoldás: https://kodein.org/Kodein-DI/