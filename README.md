# ForestFireModel
Implementation of the Drossel and Schwabl definition of the [forest fire model](https://en.wikipedia.org/wiki/Forest-fire_model) on a 2 dimensional grid.

Each cell in the grid can be in three distinct states: empty, burning and tree.

The cell evolves according to the following rules:
* A burning cell turns into an empty cell
* A tree will burn if at least one neighbour is burning
* A tree ignites with a given probability even if no neighbour is burning
* An empty space fills with a tree with a given probability
