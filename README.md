# TFS_QA_Homework6

Тестирование следующих страниц: 

- https://moscow-job.tinkoff.ru/

- https://www.tinkoff.ru/mobile-operator/tariffs/ 

- https://www.tinkoff.ru/mobile-operator/documents/

Испльзована JDK 10.0.2

Команда для запуска тестов:

```bash
-Dtest=JobTest,MobileTest verify
```

Команда для удаленного запуска тестов (требуется уже запущенный хаб):

```bash
-Dtest=JobTest,MobileTest -Dbrowser=chrome_remote verify
```