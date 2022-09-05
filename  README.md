## Клиент для BalanceService

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