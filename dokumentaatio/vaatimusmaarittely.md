SOVELLUKSEN TARKOITUS

Toteutetaan yksinkertaistettu genomiselain, jonka avulla voi etsiä, mistä lajista tuntematon DNA-sekvenssi on peräisin.
Sovellusta voi käyttää kirjautuneena tai kirjautumatta. Tiedonhaku on mahdollista toteuttaa kirjautumatta sovellukseen. Lajien genomien
lataaminen tietokantaan edellyttää tunnuksen tekemistä ja sisään kirjautumista. Tällöin voi mahdollisesti myös nimetä pätkiä genomeista 
tietyiksi geeneiksi. Koska todellisuudessa useiden lajien genomien koko on miljardien emäksien suuruusluokassa, tässä työssä ei ladata 
tietokantaan oikeita genomeita.

KÄYTTÄJÄT

* Peruskäyttäjä: Voi hakea tietokannasta tietoa vertaamalla omaa DNA-sekvenssiään tietokannan sekvensseihin siten, että käyttäjälle
palautetaan laji, josta kyseinen sekvenssi on peräisin. On mahdollista, että sovellusta kehitetään siten, että käyttäjälle palautetaan
lajit, jotka parhaiten vastaavat käyttäjän DNA-sekvenssiä. Tällöin käyttäjän sekvenssin ei tarvitse täysin sisältyä palautettaviin
sekvensseihin.
 * Erikoiskäyttäjä: Peruskäyttäjän toiminnallisuudet sisältyvät. Lisäksi erikoiskäyttäjän on mahdollista ladata ja nimetä DNA-sekevnssejä
tietokantaan. On myös mahdollista, että sovellusta kehitetään siten, että erikoiskäyttäjä voi lisätä geenejä tietokantaan.

SUUNNITELTU TOIMINNALLISUUS

* kirjautumatta: 
  * käyttäjä voi luoda järjestelmään käyttäjätunnuksen ja salasanan (TEHTY)
    * Käyttäjätunnuksen tulee olla uniikki ja salasanan vähintään 6 merkkiä pitkä (TEHTY)
  * käyttäjä voi kirjautua järjestelmään (syötetään käyttäjätunnus ja salasana) (TEHTY)
    * Järjestelmä ilmoittaa, jos käyttäjää ei ole luotu (TEHTY)
  * käyttäjä voi liittää tekstikenttään DNA-sekvenssin pätkän ja hakea, mistä lajista tai lajeista sekvenssi on peräisin. Jos käyttäjän
  sekvenssi ei vastaa mitään lajia, käyttäjälle kerrotaan tästä (TEHTY)
  
* kirjautuneena:
  * käyttäjä voi liittää tekstikenttään DNA-sekvenssin pätkän ja hakea, mistä lajista sekvenssi on peräisin (TEHTY)
  * käyttäjä voi luoda järjestelmään uuden DNA-sekvenssin (TEHTY)
    * Järjestelmä tarkastaa, että sekvenssi sisältää vain kirjaimia a,t,c ja g (TEHTY)
    * Luotu sekvenssi tulee nimetä jonkin lajin mukaan
      * Järjestelmä tarkastaa, että lajia ei ole vielä tietokannassa (TEHTY)
  * käyttäjä voi kirjautua ulos (TEHTY)
  
* Jatkokehitysideoita:

  * Toiminnallisuus, jonka avulla haun tuloksena voi saada muitakin kuin täysin haettua sekvenssiä vastaavan lajin. Järjestelmä siis 
  näyttää käyttäjälle myös lajeja, jotka vastasivat suurimmaksi osaksi oikeaa
    * Toteutetaan etsimällä samankaltaisuuksia ja pisteyttämällä siten, että c:n muuttuminen g:ksi ja päinvastoin tuottaa vähemmän
    "virhepisteitä" kuin c:n muuttuminen a:ksi tai t:ksi. Vastaavasti toimii a:n muuttuminen t:ksi ja päinvastoin. Jos "virhepisteitä" on
    kertynyt tarpeeksi eli sekvenssit ovat liian erilaiset, ei kyseistä tulosta näytetä käyttäjälle. Käyttäjälle näytetään siis ainoastaan
    riittävän samankaltaiset sekvenssit ja niiden pisteytykset ymmärrettävässä muodossa.
  * Toiminnallisuus, jonka avulla joitain tiettyjä DNA-sekvenssin pätkiä voidaan määrittää geeneiksi. Geenit tietyin pienin muutoksin 
  voivat löytyä yli lajirajojen (tämän järkevä toteuttaminen vaatii, että edellinen jatkokehitysidea on toteutettu). Erikoiskäyttäjä 
  lisää geenit järjestelmään. Peruskäyttäjän hakukenttä ei muutu mutta hänelle ilmoitetaan, lajin lisäksi se, että jos hänen hakemansa 
  sekvenssi on osa jotain järjestelmään ladattua kyseisen lajin geeniä
    