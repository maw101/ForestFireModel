# ForestFireModel

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/53c09537cdb540babb093e766e9a5016)](https://app.codacy.com/manual/maw101/ForestFireModel?utm_source=github.com&utm_medium=referral&utm_content=maw101/ForestFireModel&utm_campaign=Badge_Grade_Dashboard)

Implementation of the Drossel and Schwabl definition of the [forest fire model](https://en.wikipedia.org/wiki/Forest-fire_model) on a 2 dimensional grid.

Each cell in the grid can be in one of three distinct states: empty, burning and tree.

The cell evolves according to the following rules:
* A burning cell turns into an empty cell
* A tree will burn if at least one neighbour is burning
* A tree ignites with a given probability even if no neighbour is burning
* An empty space fills with a tree with a given probability

## Cell Evolution Probabilities
* A tree igniting where no neighbours are burning = 0.15 (15% chance)
* An empty space filling with a tree = 0.5 (50% chance)

## Demo
### GUI
An example of the simulation running with a one second delay between evolutions.

Utilises multi threading so the JavaFX application thread is not being blocked all the time.

Cell states are represented by the following three states colours:
* Green = Tree
* Red = Burning Tree
* Black = Unoccupied

![](README_ASSETS/gui_demo.gif)

### CLI
An example of the simulation running with a half second delay between evolutions.

Cell states are represented by the following three states:
* T = Tree
* B = Burning Tree
* Blank Cell = Unoccupied

![](README_ASSETS/cli_demo.gif)
