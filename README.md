# Лабораторная работа 4

## Вариант `1580`

<img alt="anime" src="./.resources/anime.gif" height="281">

> web-3-1.0-SNAPSHOT<b>.war</b>... web-3-1.0-SNAPSHOT<b>.war</b> never changes.

|.pdf|.docx|
|-|-|
| [report](./docs/report.pdf) | [report](./docs/report.docx) |

> [!TIP]
> На решение некоторых проблем в этой лабораторной работе я потратил непозволительно много времени, и не хочу, чтобы вы потратили еще столько же.
> 
> Поэтому я написал пару небольших гайдиков с советами, которые помогли мне запустить эту лабу, и, надеюсь, помогут и вам.
> 
> * Руководство по удаленному подключению _JConsole_ и _VisualVM_ к WildFly на **Helios**'е в [HELIOS_GUIDE.md](./guides/HELIOS_GUIDE.md).
> * Руководство по сборке и запуску **HttpUnit** (а также небольшие советы) в [HTTP_UNIT_GUIDE.md](./guides/HTTP_UNIT_GUIDE.md).
> * Кратко про **JFR** в [JFR_GUIDE.md](./guides/JFR_GUIDE.md).

---

## Задание

1. Для своей программы из [лабораторной работы #3](https://se.ifmo.ru/courses/web#lab3) по дисциплине __"Веб-программирование"__ реализовать:
   - `MBean`, считающий общее число установленных пользователем точек, а также число точек, не попадающих в область. В случае, если пользователь **совершил 3 "промаха" подряд**, разработанный `MBean` должен **отправлять оповещение об этом событии**.
   - `MBean`, определяющий **процентное отношение "попаданий"** к общему числу кликов пользователя по координатной плоскости.
2. С помощью утилиты `JConsole` провести мониторинг программы:
   - Снять показания `MBean`-классов, разработанных в ходе выполнения *задания 1*.
   - Определить время (в *мс*), прошедшее с момента запуска виртуальной машины.
3. С помощью утилиты `VisualVM` провести мониторинг и профилирование программы:
   - Снять график изменения показаний `MBean`-классов, разработанных в ходе выполнения *задания 1*, с течением времени.
   - Определить **имя класса, объекты которого занимают наибольший объём памяти JVM**; определить пользовательский класс, в экземплярах которого находятся эти объекты.
4. С помощью утилиты `VisualVM` и профилировщика `IDE NetBeans`, `Eclipse` или `Idea` локализовать и устранить проблемы с производительностью в [программе](https://se.ifmo.ru/documents/10180/189115/HttpUnit.tar.gz/7bf1032e-d16e-be85-c71b-dbe73c0178ba?t=1651168887037&download=true). По результатам локализации и устранения проблемы необходимо **составить отчёт**, в котором должна содержаться следующая информация:
   - Описание выявленной проблемы.
   - Описание путей устранения выявленной проблемы.
   - Подробное (со скриншотами) описание алгоритма действий, который позволил выявить и локализовать проблему.

Студент должен обеспечить возможность воспроизведения процесса поиска и локализации проблемы по требованию преподавателя.

### Отчёт по работе должен содержать:

1. Текст задания.
2. Исходный код разработанных `MBean`-классов и сопутствующих классов.
3. Скриншоты программы `JConcole` со снятыми показаниями, выводы по результатам мониторинга.
4. Скриншоты программы `VisualVM` со снятыми показаниями, выводы по результатам профилирования.
5. Скриншоты программы `VisualVM` с комментариями по ходу поиска утечки памяти.
6. Выводы по работе.

### Вопросы к защите лабораторной работы:

1. **Мониторинг** и **профилирование**. Основные понятия. Отличия мониторинга от профилирования.
2. Инфраструктура для организации мониторинга и профилирования в составе `JDK`. `JMX`.
3. `MBeans`. Основные понятия. Архитектура фреймворка.
4. Утилита `JConsole`. Возможности, область применения.
5. Утилита `Visual VM`. Возможности, область применения.
6. Удалённый мониторинг и профилирование приложений на платформе Java.

---

## Как запустить?

```bash
docker compose up       # Setup PostgreSQL database
npm install             # Install webpack
npm run build           # Run webpack
./gradlew flywayMigrate # Database migrations
./gradlew flywayInfo    # Check everything is OK
./gradlew test          # Run tests
./gradlew build         # Build .war
```

После чего задеплоить `build/libs/mispi-4-1.0-SNAPSHOT.war` в WildFly.

В случае возникновения трудностей с Helios'ом или сборкой HttpUnit, прочитай [HELIOS_GUIDE](./guides/HELIOS_GUIDE.md) и [HTTP_UNIT_GUIDE](./guides/HTTP_UNIT_GUIDE.md).

## Полезные ссылки

| Ссылка                                                                                                               | Описание                              |
|----------------------------------------------------------------------------------------------------------------------|---------------------------------------|
| [docs.oracle.com/.../jconsole.html](https://docs.oracle.com/javase/8/docs/technotes/guides/management/jconsole.html) | Документация JConsole                 |
| https://visualvm.github.io/gettingstarted.html                                                                       | Документация VisualVM                 |
| https://habr.com/ru/companies/timeweb/articles/719434/                                                               | Анализ дампа кучи Java                |
| https://habr.com/ru/companies/zabbix/articles/342226/                                                                | Кратко про мониторинг в Java          |
| https://habr.com/ru/articles/147008/                                                                                 | Гайд по запуску JConsole и VisualVM   |
| https://www.baeldung.com/visualvm-jmx-remote                                                                         | Конфигурация и использование VisualVM |
| https://github.com/alex-grandson/edu/blob/main/MISPI/lab4.md                                                         | Популярные вопросы по ЛР4             |
| [VeraKasianenko/Fundamentals_of_SE/lab4](https://github.com/VeraKasianenko/Fundamentals_of_SE/tree/main/lab4-part1)  | Пример ЛР4                            |

### Полезные материалы по JMX и прочему для сдачи теории:

При сдаче последнего задания лабораторной работы вам также могут понадобиться:
- [**Eclipse MAT**](https://eclipse.dev/mat/) -- [гайд](https://habr.com/ru/articles/519830/);
- [**Java Flight Recorder**](https://docs.oracle.com/javacomponents/jmc-5-4/jfr-runtime-guide/about.htm) (JFR) -- [гайд](https://habr.com/ru/companies/krista/articles/532632/).


| № | Ссылка | Описание |
| --- | --- | --- |
| 1. | https://docs.oracle.com/en/java/javase/21/jmx/jmx-technology-architecture.html | JMX Technology Architecture |
| 2 | https://docs.oracle.com/en/java/javase/21/docs/api/java.management/javax/management/package-summary.html | Docs for `javax.management` |
| 3 | https://en.wikipedia.org/wiki/Java_Management_Extensions#Managed_beans | Wiki MBeans |
| 4 | https://docs.oracle.com/javase/tutorial/jmx/remote/custom.html | Creating a Custom JMX Client |
| 5 | https://stackoverflow.com/questions/59696935/how-to-connect-to-jmx-for-jvm-on-local-machine-by-pid-java-9 | How to connect to JMX for JVM on local machine by PID |
| 6 | https://docs.oracle.com/cd/E19206-01/816-4178/6madjde4b/index.html | Instrumentation Using MBeans |
| 7 | https://docs.oracle.com/cd/E21764_01/web.1111/e13729/understanding.htm#JMXPG114 | Understanding JMX |
| 8 | https://docs.oracle.com/en/java/java-components/jdk-mission-control/8/user-guide/real-time-jmx-monitoring.html | JDK Mission Control User Guide: Real-time JMX Monitoring |
| 9 | https://docs.oracle.com/javase/8/docs/technotes/guides/visualvm/profiler.html | VisualVM Profiling |

## Лицензия <a name="license"></a>

Проект доступен с открытым исходным кодом на условиях [Лицензии MIT](https://opensource.org/license/mit/).

*Авторские права 2024 Max Barsukov*

**Поставьте звезду :star:, если вы нашли этот проект полезным.**
