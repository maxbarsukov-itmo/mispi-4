# Как запустить 4 лабу по ОПИ на Helios, не привлекая внимания санитаров?

<img alt="anime-angry.gif" src="./.resources/anime-angry.gif" height="290">

> Пока что единственный известный способ

> [!TIP]
> Если у вас после прочтения этого гайда всё ещё ничего не запускается/всё падает/всё ломается, пишите мне в Telegram: [t.me/@nyapsilon](https://t.me/nyapsilon).

---

## Helios

* Используемая версия **Java**: `openjdk version "17.0.2" 2022-01-18`;
* Используемая версия **WildFly**: `29.0.1.Final`.
* _Работоспособность с другими версиями возможна, но не гарантируется гайдом. Экспериментируйте на свой страх и риск._

### Пошаговое руководство

1. Скачиваем на гелиос чистый **WildFly** и распаковываем.
2. Меняем дефолтные порты на незанятые в `$JBOSS_HOME/standalone/configuration/standalone.xml`.

> Примерно `509` строка, ищите

 ```xml
<socket-binding-group name="standard-sockets" default-interface="public" port-offset="${jboss.socket.binding.port-offset:0}">
```

3. Увеличить *Metaspace* (кучу по надобности):

```bash
export _JAVA_OPTIONS='-XX:MaxHeapSize=1G -XX:MaxMetaspaceSize=512m'
```

4. `bin/standalone.sh` можно **не менять**. Проверено, что запустить можно и не изменяя опции JBoss'а. Будьте аккуратны, подобное может вызвать непонятные, не связанные с изменениями и постоянно меняющиеся ошибки :trollface:.

5. Создаем нового пользователя (в директории *WildFly*'я):

```bash
./bin/add_user.sh
# Выбираем Management User
# Юзернейм, пароль - любые
# Группа - пустая
```

6. Запускаем:

* Если интересует только **JConsole**, можно запускать так: `./bin/standalone.sh -b 0.0.0.0 -bmanagement 0.0.0.0`.

* Если нужно подключать еще и **VisualVM**, требуется изменить настройки _JMX_:

```bash
./bin/standalone.sh -b 0.0.0.0 \
    -Dcom.sun.management.jmxremote \
    -Dcom.sun.management.jmxremote.authenticate=false \
    -Dcom.sun.management.jmxremote.ssl=false \
    -Dcom.sun.management.jmxremote.port=7203 \
    -Dcom.sun.management.jmxremote.rmi.port=7203 \
    -Djava.rmi.server.hostname=0.0.0.0 \
    -Djboss.bind.address.management=0.0.0.0 \
    -Djboss.bind.address=0.0.0.0
```

---

## Свой компьютер

* Используемая версия **Java**: `21.0.2-open`.
* _Возможно, некоторые манипуляции с JConsole (в случае ошибки `sun.misc.Unsafe not found`) придется проводить с версией Java `1.8`_.

### JConsole

1. Скачать себе с *Helios* через `scp` файл `$JBOSS_HOME/bin/client/jboss-cli-client.jar`.

2. **Прокинуть порты**: обычный http порт, чтобы тыкать лабу (*8080 by default*), и management порт (*9990 by default*)

```bash
ssh -L localhost:PORT1:0.0.0.0:PORT1 localhost:PORT2:0.0.0.0:PORT2 sXXXXXX@se.ifmo.ru -p 2222 -N
```

3. Запустить *JConsole* ***этой командой (!)***:

```bash
jconsole -J-Djava.class.path=%путь_до_%jboss-client.jar
```

4. В JConsole выбираем `Remote Process` и вводим туда URL такого типа (`PORT` - это **management** порт):

```
service:jmx:remote+http://0.0.0.0:PORT
```

5. Вводим имя пользователя и пароль, которые создали во время исполнения на `./bin/add_user.sh` на **Helios**.

6. Нажимаем `Connect` и молимся, чтобы подключилось!

### VisualVM

1. Установить **VisualVM** (проще всего это делать через [sdkman.io](https://sdkman.io/), но можно и скачать с [их сайта](https://visualvm.github.io/download.html)).
2. Запускаем **VisualVM**.
3. Подключаемся удаленно **не (!) через Remote в дереве слева**, а через `File > Add JMX Connection`.
4. В `Connection:` вводим `service:jmx:remote+http://0.0.0.0:PORT` где (`PORT` - это **management** порт), ставим флаги `Use security credentials`, `Save security credentials` и `Do not require SSL connection`.
5. Вводим имя пользователя и пароль, которые создали во время исполнения на `./bin/add_user.sh` на **Helios**.
6. Нажимаем `OK` и молимся, чтобы подключилось!

---

## Лицензия <a name="license"></a>

Проект доступен с открытым исходным кодом на условиях [Лицензии MIT](https://opensource.org/license/mit/).

*Авторские права 2024 Max Barsukov*

**Поставьте звезду :star:, если вы нашли этот проект полезным.**
