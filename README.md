# How to build
Go to project directory, run `mvn clean package`
# How to run after build
In project directory, run `java -cp target/classes rx.practice.design.VillageYardSimulator`

Amount of days can be given at the end of the command, e.g. `java -cp target/classes rx.practice.design.VillageYardSimulator 10` will simulate for 10 days. Otherwise, the default days of simulation is 10

# Design Principles Applied

> Programming to an interface, not an implementation

There are the following key `interface`:

* `Animal` with a skeletal implementation, `AbstractAnimal`
* `Food` whose implementations are simple `enum`

