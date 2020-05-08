# VIIKKO2

### 23.03.2020 1,5h
Tein harjoitustyöhön tarvittavat kansiot samalla pohtien harjoitustyön aihetta. Luin ohjeita harjoitustyön tekemiseen ja aiheen valintaan. 

### 24.03.2020 2,5h
Päätin lopullisesti harjoitustyön aiheen ja kirjoitin harjoitustyön alustavan määrittelydokumentin.

# VIIKKO3

### 29.03.2020 1h
Tein ohjelmaan alustavia rakenteita ja varmistin, että repositorioni juuresta löytyy Maven-projekti.

### 30.03.2020 5,5h
Yritin rakentaa tietokantaa, johon voi lisätä sekvenssejä ja toteuttaa hakutoiminnallisuutta. En päässyt eteenpäin siitä, että sql:n 
Driver Manageria ei löytynyt. En myöskään osannut etsiä tietokannan polkua ohjelman ja tietokannan välistä yhteyttä varten.

### 30.03.2020 1h
Sain DriverManagerin toimimaan lisäämällä jbcd-ajurin Dependencies-kohtaan pom.xml-tiedostoon.

### 31.03.2020 6h
Minulla oli edelleen erittäin paljon ongelmia Sekvenssit-tietokannan kanssa. Lopulta päätin toteuttaa toiminnallisuutta ohjelmaan luomalla
käyttäjille tietokannan sijaan listan ja siten pystyin toteuttamaan tunnuksen luomisen ja sisäänkirjautumisen. En tehnyt vielä tällä 
viikolla tietokantaa vaativaa haku-toiminnallisuutta ohjelmaan. 

Sain luomani testit toimimaan.

# VIIKKO4

### 03.04. 4h
Tein ohjelmalle graafisen käyttöliittymän, jonka avulla voi kirjautua sisään ja luoda tunnuksen. Tein näihin toimintoihin tarvittavat 
näkymät. Tähän liittyen aion muuttaa toimintaa vielä siten, että graafiseen käyttöliittymään tulostuu sopiva virheviesti, jos ei syötä
oikeanlaisia arvoja.

### 07.04. 4h
Tein ohjelmalle yhden testin lisää. En voinut tehdä enempää, sillä tämän viikon tavoitteenani ole tehdä graafinen käyttöliittymä.
Korjasin README.md -dokumentin puuteet sekä tein luokkakaavion sovelluksen luokista. Näiden lisäksi tein testikattavuusraportin.

# VIIKKO5 

25,5h (edelliset) +9,5h (viikko5) =35h (yhteensä)

### 13.04. 4h
Tein sovellukseen toiminnot siihen, että erityiskäyttäjä voi lisätä sekvenssejä ja lajien nimiä, jolloin ne tulevat tavallisen käyttäjän ja toisten erikoiskäyttäjien haettavaksi. Tein testit uutta koodia varten.

### 14.04. 5,5h 
Korjasin kansiorakennettani ohjaajan kommentin mukaisesti. Viimeistelin testit ja katsoin, että testikattavuusraportin tuottaminen on mahdollista. Noudatin viikon 5 ohjeita.

# VIIKKO6

35h (edelliset) + 11h (viikko6) = 46h (yhteensä)

### 23.04. 1h
Korjasin checkstyle-virheet

### 25.04. 4h
Yritin saada tietokannan totetumaan. En onnistunut.

### 26.04. 1h
Vaihdoin suunnitelmaa tietokanna tekemisen sijaan tiedon tallentamisesta tekstitiedostoon.

### 27.04. 3h
Toteutin uuden käyttjän luomisen ja sisään kirjautumisen siten, että tieto tallentuu tekstitiedostoon. Vielä tulisi toteuttaa tämä siten, että testaus ei häiriinny, sillä tällä hetkellä testaus häiriintyy, jos tekstitiedostoa ei ole tyhjennetty edellisen testauksen jälkeen.

### 28.04. 2h
Checkstylien korjaus. ALustavan käyttöohjeen kirjoittaminen. Javadocin käyttöönotto. Arkkitehtuurikuvauksen kirjoittaminen jäi tekemättä ajanpuutteen vuoksi.

# VIIKKO7

### 03.05. 1,5
Yritin saada hakutoimintoa toimimaan sekä sekvenssitietojen että käyttäjätietojen osalta. Sovellus ei edennyt lainkaan.

### 04.05. 5h
Hioin sekä laji ja sekvenssidatan että käyttäjätietojen tallennuksia omiin tekstitiedostoihinsa siten, että metodeista ei tulisi liian pitkiä ja hankalasti ymmärrettäviä.

### 05.05. 4h
Sain tiedon tallennuksen ja haun toimimimaan siten, että sovellus käyttää työmuistia, jonne siirretään käynnistettäessä tekstitiedostoissa tallessa olleet tiedot. Sovellusta käytettäessä tekstitiedostoja ei lueta mutta työmuistia päivitetään sekä tekstitiedostoihin lisätään dataa. 

### 06.06. 6h
Hioin sovelluksen ulkoasua kuntoon. Tarkistin, että sovellus toimii virheellisilläkin syötteillä. Viimeistelin testausta.
