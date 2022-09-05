## Клиент для BalanceService

### Процесс запуска
1. Выполнить команду `mvn clean package`
2. В директории target запустить исполяемый jar-архив `java -jar BalanceClient-0.1.0-SNAPSHOT.jar`
3. Примеры запуска
```shell
java -jar BalanceClient-0.1.0-SNAPSHOT.jar -s http://localhost:8080 -f data.txt
java -jar BalanceClient-0.1.0-SNAPSHOT.jar -s http://localhost:8080 -r 10 -w 10 -i 18-20,3,2
```
4. Для запуска по данным из файла используется аргумент `-f`. Формат файла следующий
```shell
<rCount>
<wCount>
<idList>
```
5. Пример данных в файле
```shell
100
100
13,6-8,4,3,11
```

Клиент может запускать несколько конкурентных потоков на определённом подмножестве идентификаторов
- rCount - количество читателей вызывающих метод getAmount(id)
- wCount - количество читателей вызывающих метод addAmount(id,value)
- idList - список или доапазон ключей которые будут использоваться для тестирования
  Эти параметры можно задавать через командную строчку или конфигурационный файл.

Команды для запуска:
- -f,--filePath <arg>    input file path
- -i,--idList <arg>      input idList (for example: 13,6-8,4)
- -r,--rCount <arg>      input rCount
- -s,--serverUrl <arg>   input server Url
- -w,--wCount <arg>      input wCount
