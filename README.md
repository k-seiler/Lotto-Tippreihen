# Dokumentation

## Für Nutzer

Mit diesem Tool können Tippreihen für Lotto6aus49 und Eurojackpot generiert werden. Außerdem ist die Definition von Unglückszahlen möglich, die nicht in den Tippreihen vorkommen sollen. Die Bedienung erfolgt über die Kommandozeile. Durch Ausführen der Datei “JavaTippreihenGenerator.bat” wird das Tool gestartet.

Nach dem Start des Tools wird zunächst abgefragt, ob eine Tippreihe für Lotto oder für Eurojackpot erstellt werden soll. Das Tool bietet hierfür die möglichen Eingaben [euro] und [lotto]. Durch Eingabe des jeweiligen Wortes ohne eckige Klammern in der Kommandozeile wird die entsprechende Option ausgewählt.

Nach Auswahl des Lottospiels können die Unglückszahlen verwaltet werden. Hierfür bietet das Tool drei Optionen: Unglückszahlen hinzufügen bis zu einer maximalen Anzahl von 6, hinzugefügte Unglückszahlen löschen und Fortfahren zum Erstellen der Tippreihe. Eingegebene Unglückszahlen bleiben gespeichert, auch wenn das Tool beendet wird.

Wenn die Verwaltung der Unglückszahlen abgeschlossen ist, erstellt das Tool eine Tippreihe für das gewählte Spiel, welche keine der gespeicherten Unglückszahlen erhält. Die Tippreihe wird dann aufsteigend sortiert im Ausgabefenster angezeigt. Durch Eingabe der entsprechenden Option kann nun das Tool von vorne gestartet werden, um eine neue Tippreihe zu erstellen. Ansonsten wird es beendet.

Beim ersten Ausführen werden im Speicherort des Tools zwei neue Dateien erstellt. Die Datei “unglueckszahlen.txt” dient zur Speicherung der eingegebenen Unglückszahlen. Die Datei “logfile.log” protokolliert den letzten Programmablauf und kann im Falle von Fehlern analysiert werden.



## Für Entwickler

Zur Umsetzung des Tools in Java wurden insgesamt 8 Klassendokumente erstellt. Diese werden hier zusammen mit ihren wichtigsten Bestandteilen kurz erläutert. Der Ordner lottoTipppreihenGenerator enthält das komplette Java-Projekt.

### Tippreihe
Diese Klasse ist ein Interface, welches die allgemeinen Methoden zur Generierung von Tippreihen definiert.

### LottoTippreihe
Die Klasse LottoTippreihe implementiert das Tippreihe-Interface und dessen Methoden, was die Erstellung generischer Tippreihen-Objekte ermöglicht.

**Attribute:**

int maxAnzahl: Anzahl der Tipps in einer Tippreihe
 
int maxWert: Höchstwert für den Tipp in einem Spiel
 
int[] tipps: Array zum Speichern von Tipps in einer Tippreihe
 
**Methoden:**

neueTippreihe(): erstellt eine Tippreihe mit maxAnzahl aus maxWert Zufallszahlen. Wenn eine erzeugte Zufallszahl eine Unglückszahl ist, wird sie erneut erstellt. Die Tippreihe wird aufsteigend sortiert im Array tipps gespeichert.
 
ausgabe(): gibt einen String zurück, in dem die Tippreihe in lesbarer Form dargestellt wird.

### Lotto6aus49
Die Klasse Lotto6aus49 erweitert die Klasse LottoTippreihe. Mit ihr werden Tippreihen für ein 6aus49-Spiel generiert.

### Eurojackpot
Die Klasse Eurojackpot erweitert ebenfalls die Klasse LottoTippreihen. Mit ihr werden Tippreihen für ein 5aus50-Spiel mit 2aus10-Bonuszahlen generiert.

**Attribute:**

LottoTippreihe bonuszahlen: Die 2aus10-Bonuszahlen werden durch ein Attributsfeld der Klasse realisiert. Wird eine neue Eurojackpot-Tippreihe generiert, so wird für die Bonuszahlen eine neue 2aus10-Tippreihe erstellt.

### Unglueckszahlen
Mit der Klasse Unglueckszahlen werden die Zahlen verwaltet, welche bei der Generierung von Tippreihen übersprungen werden sollen.

**Attribute:**

ArrayList<Integer> zahlen: hier werden die aktiven Unglückszahlen gespeichert.

**Methoden:**

init(): Wird bei Programmstart aufgerufen. Wenn eine Speicherdatei für Unglückszahlen existiert, werden die in ihr enthaltenen Zahlen geladen, ansonsten wird diese Datei erstellt.

ausgabe(): Gibt einen String zurück, in dem die derzeit aktiven Unglückszahlen in lesbarer Form dargestellt werden.

hinzufuegen(String): Dieser Funktion werden Nutzerinputs übergeben, die als Unglückszahl hinzugefügt werden sollen. Ist der Input eine valide Unglückszahl wird sie der ArrayList hinzugefügt. Die Funktion gibt einen String zurück, der ein lesbares Feedback für den übergebenen Input enthält (wurde die Zahl als Unglückszahl hinzugefügt? Falls nein, warum nicht?)

entfernen(String): Die Funktionsweise entspricht der hinzufügen-Funktion, mit dem Unterschied dass hier der Nutzerinput nicht als Unglückszahl hinzugefügt, sondern gelöscht wird.

speichern(): Diese Funktion schreibt die aktuellen Unglückszahlen in die Speicherdatei. Sie wird aufgerufen, wann immer eine Unglückszahl hinzugefügt oder entfernt wird.

getZahlen(): Gibt die derzeit aktiven Unglückszahlen als Integer-Array zurück. Bei der Generierung von Tippreihen wird diese Methode genutzt, um sicherzustellen dass keine Unglückszahlen enthalten sind.

### UnglueckszahlenTest
Die Klasse UnglueckszahlenTest beinhaltet einige UnitTests für die verschiedenen Funktionalitäten der Unglueckszahlen-Klasse

### Logfile
Die Klasse Logfile dient zur Erstellung einer Protokolldatei, in welcher der Programmablauf protokolliert wird. 

**Attribute:**

Logger logger: Der Logger wird in verschiedenen Methoden und Klassen aufgerufen, um einen neuen Eintrag in die Protokolldatei zu schreiben, wenn sich Änderungen ergeben oder Nutzerinputs verarbeitet werden.

**Methoden:**

init(): Wird bei Programmstart aufgerufen. Der Logger wird generiert und ihm wird die Datei “logfile.log” als Speicherort zugewiesen (existiert diese Datei noch nicht, wird sie hier erstellt).

### Main
Die Klasse Main regelt die Kommunikation mit dem Nutzer. Hier werden die Ausgaben für die Kommandozeile erstellt und die Nutzerinputs angenommen.

**Methoden:**

main(): Diese Methode wird bei Programmstart aufgerufen. In ihr werden die Klassen Logfile und Unglueckszahlen initialisiert. Der Nutzer wird begrüßt, dann wird die Funktion startRoutine aufgerufen.

startRoutine(): Dem Nutzer werden die Namensparameter für die möglichen Tippreihen präsentiert. Nachdem der Nutzer seinen Parameter eingibt, wird zunächst die Funktion unglueckszahlenRoutine aufgerufen. Danach wird anhand des eingegebenen Parameters die gewünschte Tippreihe erzeugt und ausgegeben. Der Nutzer hat nun die Möglichkeit, zum Start zurückzukehren oder das Programm zu beenden.

unglueckszahlenRoutine(): Diese Methode regelt die Verwaltung der Unglückszahlen. Durch die entsprechenden Namensparameter bekommt der Nutzer die Möglichkeit, Unglückszahlen hinzuzufügen, zu löschen oder die Routine zu verlassen oder zur Erstellung der Tippreihe fortzufahren. Hier werden die Methoden der Klasse Unglueckszahlen verwendet, welchen die Nutzerinputs als Parameter übergeben werden.
