# Yatzy Desktop Applikation (JavaFX)

Dette projekt er en fuldt funktionel Yatzy-spilapplikation udviklet i Java ved brug af JavaFX til brugergrænsefladen. Applikationen giver en spiller mulighed for at gennemføre et spil Yatzy, inklusiv terningkast, fastholdelse af terninger og scoring af kombinationer i henhold til de klassiske regler.


---

## Teknisk Struktur & Demonstrerede Færdigheder

Projektet er struktureret efter **Model-View-Controller (MVC)**-designprincippet, hvilket sikrer en klar adskillelse mellem spillets logik og GUI'en. Dette er med til at skabe velstrukturerede og vedligeholdelsesvenlige applikationer.

### Nøglekomponenter:

| Fil/Klasse | Ansvar | Demonstreret Færdighed |
| :--- | :--- | :--- |
| `YatzyResultCalculator.java` | Spillets kerne-logik. Håndterer pointberegning for alle 15 kombinationer (fra 1'ere til Yatzy, inkl. straight, fuldt hus osv.). | **Kompleks Algoritmeimplementering** og effektiv brug af **Arrays**. |
| `YatzyGui.java` / `*Pane.java` | Den visuelle præsentation og brugerinteraktion (terningvisning, pointtavle og knapper). | Anvendelse af **JavaFX** til GUI-design og opbygning af layout. |
| `YatzyController.java` | Koordinerer dataflowet: Tager input fra GUI'en og kalder den nødvendige logik, og opdaterer derefter GUI'en. | Sikring af adskillelse mellem **funktionalitet og brugergrænseflade (MVC)**. |

---

## Baggrund og Kreditering

Projektet er udarbejdet som svar til afleverings opgave 3 "Projekt 3 - Yatzy" i Programering 1, på Datamatiker uddannelsen på Aarhus Erhvervsakadami. 

* **Teknologi:** Java, JavaFX.
* **Originalt Udgangspunkt:** Projektet blev forket fra et start-repository leveret af underviser **Benn Christensen**.
* **Mit Bidrag:** Jeg har selvstændigt implementeret *al* spil-logik i `YatzyResultCalculator.java` (som verificeres af tests) og designet, udviklet og forbundet *hele* den interaktive JavaFX-brugerflade for at skabe den færdige desktop-applikation.

## Hvordan kører man applikationen?

1.  **Klon repositoryet:** `git clone https://github.com/GustavFL2000/Yatzy`
2.  **Krav:** Sørg for at have et JDK installeret, der understøtter JavaFX (f.eks. ved brug af en moderne build-fil som Maven eller Gradle, der håndterer afhængigheder).
3.  **Kør:** Kør projektets hovedklasse: `gui.YatzyApp`.

---

## 🔗 Prøv Spillet i Din Browser (Web-version)

For at gøre spillet let tilgængeligt i min portfolio, er det blevet deployet til web ved brug af JPro.

**Spil demo:** [LINK TIL DIN KØRENDE WEB-VERSION HER]
