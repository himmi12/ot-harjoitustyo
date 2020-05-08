# Testausdokumentti

Sovellusta on testattu yksikkö ja integraatiotestein sekä järjestelmätestillä, josta on luotu testikattavuusraportti.

## Yksikkö- ja integraatiotestaus

### Sovelluslogiikka

Sovellusta testataan ainoastaan fi.himmi.logics -paketissa, jonka sisällä ovat kaikki sovelluslogiikan luokat. Koska lähes kaikki sovelluksen toiminnallisuus on luokassa Biotietokanta, sitä pääasiassa testataan. Biotietokanta-luokan testiluokka on Biotietokanta.test, joka käyttää omia tekstitiedostojaan usersFake.txt ja seqFake.txt tiedon tallennukseen.

### Käyttöliittymä

Käyttöliittymää ei testata.

### Testauskattavuus

![kuva](/dokumentaatio/kuvat/Testaus.png)

Testauskattavuusraportti on tehty vain paketista fi.himmi.logics, koska käyttöliittymän sisältävässä paketissa ei ole testejä. Huomataan, että sekä rivi- että haarautumakattavuus on 93 %. Näin ollen yksikkötestauksessa tulokset ovat suhteellisen kelpaavia.

## Järjestelmätestaus

Järjestelmätestaus toteutett

## Asennus ja konfigurointi

## Toiminnallisuudet

## Sovellukseen jääneet laatuongelmat
