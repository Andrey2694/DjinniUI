# Автотесты для сайта https://djinni.co/

___

## Используемые технологии и инструменты

<code><img width="5%" title="Java" src="images/JAVA.svg"></code>
<code><img width="5%" title="Gradle" src="images/Gradle.svg"></code>
<code><img width="5%" title="JUnit5" src="images/Junit5.svg"></code>
<code><img width="5%" title="Jenkins" src="images/Jenkins.svg"></code>
<code><img width="5%" title="Allure TestOps" src="images/Allure TestOps.svg"></code>
<code><img width="5%" title="Allure Report" src="images/Allure Report.svg"></code>
<code><img width="5%" title="Telegram" src="images/Telegram.svg"></code>
<code><img width="5%" title="Selenoid" src="images/Selenoid.svg"></code>
 <code><img width="5%" title="Selenium" src="images/Selenide.svg"></code>
 
</p>

___

## Инструкция по запуску тестов

Запуск тестов происходит через джобу в [Jenkins](https://jenkins.autotests.cloud/job/009-Andrey_Zhmaka-DjinniUI/)
или через консоль

```bash
gradle clean test -DremoteDriverUrl=http://65.108.161.82:4444/wd/hub/ -DvideoStorage=http://65.108.161.82:8080/video/ -Dthreads=5
```

### Используемые параметры по умолчанию

* browser (chrome)
* browserSize (1920x1080)
* baseUrl (https://djinni.co/)

### Serve report:

```bash
allure serve build/allure-results
```

## Оповещение о результатах прохождения тестов через бот в телеграмм

![Telegram](images/telegram_notifi.png)

## Анализ результатов

* Jenkins через Allure Reports или Allure TestOps

### Анализ результатов в Jenkins через Allure Reports

![alt "Allure Reports"](./images/allure_report_notifi.png)

### Анализ результатов в Allure TestOps

![alt "Allure TestOps"](./images/testOps_notifi.png)
![alt "Allure TestOps"](./images/testOps_notifi2.png)

### Интерграция с Jira
#### Отображение тест-кейсов и ланча

![jira](./images/jira.png)

### Видео с примером прохождения теста в selenoid

!["Video from Selenoid"](./images/video.gif)
