A kotprogGUI - Slenderman mappát add hozzá projektként az IDEA-ban (File > Open.. és válaszd ki a mappát)
Legalább JDK 15-ös verzió szükséges hozzá, erre az IDEA is figyelmeztetni fog.
A java doc-ot megtalálod a docs mappában (docs > index.html)
Néha megesik, hogy a Slenderman a volt poziciodra teleportál és olyankor nem látszik a képe, csak a mező lesz piros, attól még vedd úgy, hogy ott van a Slenderman hiszen, ha odalépsz, akkor elkap.

Egyéni pálya infók:

Az egyéni pálya létrehozását palya.txt-ben kell megvalósitanod. Hagyok egy mintát ott annak megfelelően kell hogy kinézzen.
15 x 15 mátrixot kell megadnod és mindegyik számnak meg van a maga jelentése, ahova rakod az adott számot az ott fog megjelenni a játékban is.
Számok jelentése:
0 - egyszerű mező
1 - kisfa (1x1)
2 - nagyfa (2x2)
3 - ház (6x7)
4 - autó (horizontális) (3x2)
5 - teherautó (vertikális) (3x5)
6 - szikla (3x3)
7 - hordó (horizontális) (4x2)
8 - a játékos kezdő poziciója (bal fenti illetve jobb lenti sarok)
9 - a tárgyek méretet foglalja le
A zárójelben pl (4x2) az első szám mindig a jobb oldali hosszúság, a második szám pedig lefelé a hosszúság (4 jobb, 2 le)

Feltételezem, hogy a pályát megfelelően hozod létre ezért nincs az összes hiba lekezelve (elvileg nem is kell).
Minimum tárgynak adjál meg 8-at különben nem is indul el a játék.
Ahova valamilyen tárgyat szeretnél rakni, pl. ha egy házat annak mindig a bal fenti sarok pozicióját adod meg tehát, ha valahova irsz egy 3-ast, akkor azt le kell foglalni lefelé 7 sorban és jobbra 6 sorban.
Ezeknek a helyfoglalását a 9-es szám adja meg. (tehát a 3-as alá kerül 6db 9-es és jobbra pedig 5db 9-es, a maradék részt pedig kitöltöd 9-el). Ahova leraktad a tárgyat közvetlen mellette balra mindig hagyjál egy 0-át!!
Ez azért fontos mert ide kerülnek majd a papirok véletlenszerű kiosztással (tehát a 3-as mellett balra 0 álljon)
0 - 9 terjedő számokat adhatsz meg, ha más egész számot adsz meg azt alapértelmezetten 0-nak kezeli. 
Ha nem egész számot adsz meg akkor a hibát lekezeli a program viszont újra kell majd valószinüleg inditanod a megfelelő számokkal, mert különben lehet hogy a tárgyakon majd át tudsz hatolni, ami nem mengengedett.
Figyelj, hogy a tárgyak ne legyenek egymáson. A ház bejáratához se tegyél semmi akadályt különben nem lehet bemenni (az ajtók pozicioja: első oszlop utolsó előtti sor, utolsó sor utolsó előtti oszlop).
A txt fájlban figyelj arra, hogy az utolsó oszlop után ne hagyjál felesleges szóközöket, illetve ugyanez igaz az utolsó sor (15. sor) utáni sorra is.

CMD infók:

Ha cmd-ből szeretnéd inditani a játékot akkor előtte másold át az img mappát és helyezd be a src-ba (Futtatas, Palya, Karakter mellé) illetve ugyanide másold be a palya.txt is.
Futtatáshoz cmdben lépj be a src mappába (cd *\src) és onnan add ki a: 
javac Futtatas\Main.java -d .
java Futtatas/Main

Figyelj hogy a második parancsban a \ átfordult /-re
Ha errort dob próbáld meg leellenőrizni, hogy a megfelelő jdk verzió van telepitve