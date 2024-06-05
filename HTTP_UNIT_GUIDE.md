# Собираем и запускаем HttpUnit

<img alt="tf2-anime-girl" src="./.resources/tf2-anime-girl.gif" height="240">

> [!NOTE]
> Здесь описывается запуск через терминал. Если вы привыкли работать в IDEA / Eclipse, то сможете сами разобраться, на какие кнопочки тыкать.

---

0. Если у вас версия Java >= `1.6`, то
   * В `nbproject/build-impl.xml` меняем `default.javac.source|target` с `1.6` на `1.8`;
   * Аналогично в `npproject/project.properties` меняем на `javac.source=1.8` на `javac.target=1.8`.

1. Чтобы собрать и запустить проект, вам понадобится [Ant](https://ant.apache.org/) (_версия >=1.8.0_);
2. **Сборка**: `ant compile`;
3. **Запуск**: `ant run`.

---

## Лицензия <a name="license"></a>

Проект доступен с открытым исходным кодом на условиях [Лицензии MIT](https://opensource.org/license/mit/).

*Авторские права 2024 Max Barsukov*

**Поставьте звезду :star:, если вы нашли этот проект полезным.**
