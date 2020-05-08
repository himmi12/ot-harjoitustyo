# Testausdokumentti

Sovellusta on testattu yksikkö- ja integraatiotesteillä, joista on luotu testikattavuusraportti, sekä järjestelmätestillä sovellusta käytettäessä.

## Yksikkö- ja integraatiotestaus

### Sovelluslogiikka

Sovellusta testataan ainoastaan fi.himmi.logics -paketissa, jonka sisällä ovat kaikki sovelluslogiikan luokat. Koska lähes kaikki sovelluksen toiminnallisuus on luokassa Biotietokanta, sitä pääasiassa testataan. Biotietokanta-luokan testiluokka on Biotietokanta.test, joka käyttää omia tekstitiedostojaan usersFake.txt ja seqFake.txt tiedon tallennukseen.

### Käyttöliittymä

Käyttöliittymää ei testata.

### Testauskattavuus

![kuva](/dokumentaatio/kuvat/testausLogics.png)
Testauskattavuusraportti on tehty vain paketista fi.himmi.logics, koska käyttöliittymän sisältävässä paketissa ei ole testejä. Huomataan, että sekä rivikattavuus on 96% ja haarautumakattavuus on 92 %. Näin ollen yksikkötestauksessa tulokset ovat suhteellisen kelvollisia.

![kuva2](/dokumentaatio/kuvat/testausBio.png)
Biotietokanta-luokan metodien testaus tarkemmin.

## Järjestelmätestaus

Järjestelmätestaus on toteutettu manuaalisesti testaamalla, että erilaisilla väärilläkin syötteillä käyttäjä saa järkevän virheviestin ja tarkemmat ohjeet, jotta sovellusta on mahdollista käyttää oikein.

## Asennus ja konfigurointi

Käyttöohjeessa täsmennettyjä toiminallisuuksia on testattu. Koska ohjelma ei luo itse tiedostoja sekvenssit.txt ja kayttajat.txt, joihin sovelluksen tiedot tallennetaan, on sovellusta testattu ainoastaan siten, että ne ovat olemassa. Myös graafisen käyttöliittymän taustan kuvan tulee olla samassa hakemistossa ohjelman kanssa, jotta graafinen käyttöliittymä näkyy oikeanlaisena.

## Toiminnallisuudet

Sovelluksen toiminnallisuudet löytyvät käyttöohjeesta. Määrittelydokumentista voi lukea näistä toiminnallisuuksista lisää. Toiminnallisuudet on katsottu toimivan myös virheellisillä arvoilla.

## Sovellukseen jääneet laatuongelmat

Sovellus on keskeneräinen, koska tekstitiedostoihin voi ainoastaan lisätä dataa mutta arvojen poistaminen on mahdotonta sovellusta käytettäessä. Koska tiedontallennussysteemi on vajavainen, sovellus ei ole erityisen käytettävä ja tiedon pysyväistallennuksen testaamista ei ole. Tämä on merkittävä puute.
