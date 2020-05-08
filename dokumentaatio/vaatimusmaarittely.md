# Vaatimusmäärittely

## Sovelluksen tarkoitus

Toteutetaan yksinkertaistettu genomiselain, jonka avulla voi etsiä, mistä lajista tuntematon DNA-sekvenssi on peräisin.
Sovellusta voi käyttää kirjautuneena tai kirjautumatta. Tiedonhaku on mahdollista toteuttaa kirjautumatta sovellukseen. Lajien genomien
lataaminen tietokantaan edellyttää tunnuksen tekemistä ja sisään kirjautumista. Koska todellisuudessa useiden lajien genomien koko on miljardien emäksien suuruusluokassa, tässä työssä ei ladata tietokantaan oikeita genomeita vaan pieniä osia niistä.

## Käyttäjät

### Peruskäyttäjä: 

Voi hakea tietokannasta tietoa vertaamalla tuntematonta DNA-sekvenssiä tietokannan sekvensseihin siten, että käyttäjälle
palautetaan lista lajeista, joista kyseinen sekvenssin pätkä on peräisin.

### Erikoiskäyttäjä: 

Peruskäyttäjän toiminnallisuudet sisältyvät. Lisäksi erikoiskäyttäjän on mahdollista ladata ja nimetä DNA-sekevnssejä
tietokantaan.

## Toiminnallisuus

### Kirjautumatta: 

  * käyttäjä voi luoda järjestelmään käyttäjätunnuksen ja salasanan
    * Käyttäjätunnuksen tulee olla uniikki ja salasanan vähintään 6 merkkiä pitkä
  * käyttäjä voi kirjautua järjestelmään (syötetään käyttäjätunnus ja salasana)
    * Ilmoitetaan, jos käyttäjää ei ole luotu
				* Ilmoitetaan, jos salasana väärin
  * käyttäjä voi liittää tekstikenttään DNA-sekvenssin pätkän ja hakea, mistä lajista tai lajeista sekvenssi on peräisin. Jos käyttäjän
  sekvenssi ei vastaa mitään lajia, käyttäjälle kerrotaan tästä
  
### Kirjautuneena:

  * käyttäjä voi liittää tekstikenttään DNA-sekvenssin pätkän ja hakea, mistä lajista sekvenssi on peräisin
  * käyttäjä voi luoda järjestelmään uuden DNA-sekvenssin
    * Järjestelmä tarkastaa, että sekvenssi sisältää vain kirjaimia a,t,c ja g
    * Luotu sekvenssi tulee nimetä jonkin lajin mukaan
				   * Järjestelmä tarkastaa, että lajia ei ole vielä lisätty tietokantaan
  * käyttäjä voi kirjautua ulos
  
## Jatkokehitysideoita:

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
    
