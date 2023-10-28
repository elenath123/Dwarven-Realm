Dwarven Realm - аддон к моду "Властелин колец" для Minecraft 1.7.10. Добавляет две новые фракции - гномы Красных гор и гномы гор Ветра, а также весь связанный с ними контент: мобы, достижения, путевые точки, дороги, щиты, флаги, блоки, предметы, структуры, зоны влияния...

https://lotrminecraftmod.fandom.com/ru/wiki/Dwarven_Realm - статья, в которой подробно описан мод.

Приведённая ниже документация будет полезна как разработчикам, так и мне самому из будущего. Сведения, гайды, подводные камни, советы и всё такое. 

## Общая информация

* Этот репозиторий - проект Gradle, который должен быть открыт через IntelliJ IDEA или импортирован в Eclipse IDE.
* Используемый синтаксис Java - 8.
* Используемая версия Gradle - 8.4.
* Используемая версия ForgeGradle – Anatawa12's 1.2-1.1.+.
* Используемая версия JDK - Eclipse Temurin 1.8.0_392.

Обязательно проконтролируйте, чтобы ваш синтаксис Java и ваша JDK были такими, как сказано выше. Ваша IDE и переменные среды вашей ОС должны быть правильно сконфигурированы, иначе ничего не будет работать.

* JDK, сконфигурированный в IDE, отвечает за то, какие синтаксические конструкции есть в языке и какой на выходе получится байткод (который должен быть совместим с ожиданиями Forge).
* Синтаксис, сконфигурированный в IDE, отвечает за то, какие конструкции языка вы можете использовать (чтобы случайно не получить на выходе байткод, который будет нечитаемым и крашнет Forge). В некоторых случаях вы можете выбрать более новую JDK и более старый синтаксис, чтобы ограничить себя.
* JDK, сконфигурированный в переменных средах ОС, отвечает за то, какой джавой будет запущена сборка мода и загрузка среды.
* JDK в Eclipse называется `JRE/JDK`, в IntelliJ IDEA - `SDK`. Синтаксис в Eclipse называется `Compiler Compliance Level`, в IntelliJ IDEA - `Language Level`.

### Где это настраивать?

* Переменные среды Windows:
  * Первый способ: `ЛКМ по "Мой компьютер" -> Свойства -> Дополнительные параметры системы -> Переменные среды`.
  * Второй способ: `Параметры -> Система -> О системе -> Дополнительные параметры системы -> Переменные среды`.
  * Когда вы всё-таки добрались до переменных сред, создайте там две переменные: `JAVA_HOME` и `Path`. Если они уже существуют, то двойным нажатием ЛКМ откройте их и посмотрите содержимое.
    * В JAVA_HOME должна быть только одна строка, содержащая путь к вашей JDK. У меня это `C:\Users\Hummel009\.jdks\temurin-1.8.0_392`.
    * В Path, помимо других строк, должна быть как минимум одна строка, содержащая путь вашей JDK\bin. У меня это `C:\Users\Hummel009\.jdks\temurin-1.8.0_392\bin`.
* Eclipse IDE:
  * Сверху справа находится лупа, через которую можно попасть куда угодно.
    * Для настройки синтаксиса введите в поиск `Compliance` и откройте предложенный `Compiler`. Там сверху выставляется число.
    * Для настройки JDK введите в поиск `Installed JREs` и откройте предложенный  `Installed JREs | Open the preferences dialog`. Там вы можете нажать на текущую JRE и удалить её (`Remove`), чтобы потом добавить (`Add`) новую `Standard VM` и указать место на диске, где лежит ваша JDK, после чего подтвердить и выбрать её.
  * Альтернативный способ добраться до настроек синтаксиса и JDK - это сверху нажать `Window -> Preferences -> Java` и там выбрать либо `Compiler`, либо `Installed JREs`.
  * Если у вас в среде много проектов, вы можете настроить каждый отдельно. Для этого нажмите слева на проект и сверху пройдите по пути `Project -> Properties` (а не `Window -> Preferences`). Там есть `Java Build Path` и `Java Compiler`.
    * `Java Compiler` - просто окно, где сверху выставляется число.
    * `Java Build Path` - окно с вкладками, где вас интересует вкладка `Libraries`. Там нужно нажать по центру на `Module path`, а затем нажать справа `Add Library -> JRE System Library`. Там вы можете указать одну из добавленных ранее в `Installed JREs`.
* IntelliJ IDEA:
  * `Ctrl + Alt + Shift + S` приведёт вас к структуре проекта, где вы настраиваете SDK и Language Level.
  * `Ctrl + Alt + S`, а затем `Build... -> Compiler -> Java Compiler` приведёт вас к настройкам Language Level в среде разработки.
  * `Ctrl + Alt + S`, а затем `Build... -> Build Tools -> Gradle -> Gradle JVM` приведёт вас к настройкам переменных сред в среде разработки. Это на случай, если для запуска Gradle нужна JDK, отличающаяся от той, что указана в переменных средах ОС.
  * Альтернативный способ добраться до структуры проекта или настроек среды - это нажать на шестерёнку сверху справа и выбрать там `Project Structure` или `Settings`.
  * Ещё один альтернативный способ найти настройки среды - нажать `File -> Settings`.

## Гайд по установке и работе

Собственно, для начала нужно скачать и разархивировать куда-то папку с исходниками. Будем называть это **папкой проекта**. В ней лежат папки (gradle, src) и различные файлы.

Версия 1.7.10 вышла достаточно давно, и с тех пор все инструменты для разработки уже устарели. К счастью, они ещё работают. Тем не менее, уже на момент создания они были неидеальны и требовали костыли. Костыли реализуются созданием файлов IDE.

* В папке проекта есть два файла с расширением .bat, `setupEclipse` и `setupIdea`. Выберите нужный, запустите двойным нажатием ЛКМ по нему, подождите окончания.
  * Под капотом файлов с расширением .bat лежат команды консоли Windows, `gradlew setupDecompWorkspace eclipse` и `gradlew setupDecompWorkspace idea`, соответственно. Если желаете взять процесс в свои руки и не полагаться на эти файлы, можете вместо их запуска открыть в папке проекта консоль Windows и ввести там эти команды руками. Смысл файлов, скорее, не в экономии времени, а в напоминании о том, что эта версия требует костыль.
* После окончания генерации среды импортируйте сгенерированный проект IDE. ***Именно проект IDE! Это не проект Gradle.***
  * В случае Eclipse IDE импортируйте папку проекта: `File -> Import -> General -> Existing Projects into Workspace -> Next -> [Выбираете папку] -> Finish`.
  * В случае IntelliJ IDEA двойным нажатием ЛКМ откройте файл `.ipr` в папке проекта. Загрузку скриптов Gradle нужно пропустить (`skip`). Когда инициализация закончится, крайне рекомендуется в меню `File -> Manage IDE Settings` конвертировать проект `.ipr` в `Directory-based format`, иначе багов и зависаний не миновать.
* Чтобы запустить мод из среды, используйте следующий подход:
  * В случае IntelliJ IDEA уже сгенерированы две конфигурации запуска, клиент и сервер (в верхней части окна). Сервер не работает, это норма. Клиент запускается с недостаточным количеством памяти. Это можно исправить, нажав на название запуска, а затем на `Edit Configurations`. Замените `1024` на `2048` (как минимум).
  * В случае Eclipse IDE придётся самостоятельно создать новую конфигурацию запуска. Это делается возле зелёного кружочка запуска в верхней части окна - там правее есть чёрный треугольник, при нажатии на который можно увидеть выпадающее меню и там пункт `Run Configurations`. Нажмите на пункт, затем выберите слева `Java Application` и нажмите сверху слева на белый документ. Появляется ненастроенный запуск. Настройте его.
    * В первой вкладке (сверху) укажите проект - это папка, для которой мы создаём запуски.
    * В первой вкладке (ниже) укажите файл входа - это `GradleStart`.
    * Во вкладке `Arguments` (сверху) выделите память: `-Xincgc -Xmx2G -Xms2G`.
    * Во вкладке `Arguments` (снизу) укажите рабочую папку (в ней будут миры майнкрафта и прочие файлы игры). Например, создайте в скачанном репозитории папку `run` и укажите её, как рабочую.
    * Сохраните.
* Чтобы скомпилировать мод в файл с расширением .jar, используйте следующий подход:
  * В случае Eclipse IDE откройте консоль Windows в папке среды и введите `gradlew build`. Альтернатива - создайте файл `build.bat`, откройте его блокнотом, впишите туда команду `gradlew build`, сохраните, запустите двойным нажатием ЛКМ.
  * В случае IntelliJ IDEA можно применить способ Eclipse, но есть и более красивый способ. Создайте собственный Gradle Task с командой `build` на вашем проекте и выполните его. Как это сделать:
    * Нажмите на название уже сгенерированного запуска в верхней части окна.
    * В выпадающем меню нажмите на `Edit Configurations`.
    * Слева сверху нажмите на значок плюса, а затем - на `Gradle` в появившемся меню.
    * Под словом `Run` напишите `build`.
    * Сохраните снизу, нажав на "ОК".
    * Для компиляции выберите тот запуск, который вы только что создали, и запустите его зелёной кнопкой запуска (треугольник).
* После того, как вы запустите сборку одним из способов выше, произойдёт компиляция, а ваш мод появится в папке `папка_проекта/build/libs`.
