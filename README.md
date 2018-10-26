# TFS_QA_Homework6

Тестирование страницы https://moscow-job.tinkoff.ru/

Испльзована JDK 10.0.2

Команда для запуска тестов в Chrome:

```bash
-Dtest=JobTest -Dbrowser=chrome verify
```

Команда для запуска тестов в Firefox:

```bash
-Dtest=JobTest -Dbrowser=firefox verify
```

Команда для запуска тестов в Opera:

```bash
-Dtest=JobTest -Dbrowser=opera "-DoperaPath=\Path\To\opera.exe" verify
```