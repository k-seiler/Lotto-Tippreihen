# Dokumentation

## Für Nutzer

Mit diesem Tool können Tippreihen für Lotto6aus49 und Eurojackpot generiert werden. Außerdem ist die Definition von Unglückszahlen möglich, die nicht in den Tippreihen vorkommen sollen. Die Bedienung erfolgt über die Kommandozeile. Durch Ausführen der Datei “JavaTippreihenGenerator.bat” wird das Tool gestartet.

Nach dem Start des Tools wird zunächst abgefragt, ob eine Tippreihe für Lotto oder für Eurojackpot erstellt werden soll. Das Tool bietet hierfür die möglichen Eingaben [euro] und [lotto]. Durch Eingabe des jeweiligen Wortes ohne eckige Klammern in der Kommandozeile wird die entsprechende Option ausgewählt.

Nach Auswahl des Lottospiels können die Unglückszahlen verwaltet werden. Hierfür bietet das Tool drei Optionen: Unglückszahlen hinzufügen bis zu einer maximalen Anzahl von 6, hinzugefügte Unglückszahlen löschen und Fortfahren zum Erstellen der Tippreihe. Eingegebene Unglückszahlen bleiben gespeichert, auch wenn das Tool beendet wird.

Wenn die Verwaltung der Unglückszahlen abgeschlossen ist, erstellt das Tool eine Tippreihe für das gewählte Spiel, welche keine der gespeicherten Unglückszahlen erhält. Die Tippreihe wird dann aufsteigend sortiert im Ausgabefenster angezeigt. Durch Eingabe der entsprechenden Option kann nun das Tool von vorne gestartet werden, um eine neue Tippreihe zu erstellen. Ansonsten wird es beendet.

Beim ersten Ausführen werden im Speicherort des Tools zwei neue Dateien erstellt. Die Datei “unglueckszahlen.txt” dient zur Speicherung der eingegebenen Unglückszahlen. Die Datei “logfile.log” protokolliert den letzten Programmablauf und kann im Falle von Fehlern analysiert werden.
