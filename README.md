# Biotietokanta
Sovellus on yksikertaistettu genomiselain, jonka avulla voi etsiä, mistä lajista tuntematon DNA-sekvenssi on peräisin. Sovellusta voi käyttää kirjautumatta, jolloin vain lajin hakeminen sekvenssin perusteella on mahdollista. Kirjautuneena käyttäjän puolestaan on lajin haun lisäksi mahdollista lisätä lajeja ja sekvenssejä tietokantaan.

## Dokumentaatio

[käyttöohje](/dokumentaatio/kayttoohje.md)

[vaatimusmäärittely](/dokumentaatio/vaatimusmaarittely.md)

[arkkitehtuurikuvaus](/dokumentaatio/arkkitehtuuri.md)

[tyoaikakirjanpito](/dokumentaatio/tyoaikakirjanpito.md)

testausdokumentti

## Releaset

[viikko 5](https://github.com/himmi12/ot-harjoitustyo/releases/tag/viikko5)

[viikko 6](https://github.com/himmi12/ot-harjoitustyo/releases/tag/viikko6)

## Komentorivikomennot

### Testaus
Testit suoritetaan komennolla: mvn test

Testikattavuusraportti luodaan komennolla: mvn jacoco:report

### Suoritettavan jarin generointi 
Komennolla: mvn package

### JavaDoc:n generointi
Komennolla: mvn javadoc:javadoc

### Checkstyle.xml -tiedoston määrittelemät tarkistukset
Komennolla: mvn jxr:jxr checkstyle:checkstyle

