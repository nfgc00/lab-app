# Lab-App

## Background

Scientists need a digital solution to store and identify various items used in their experiments.
These items come in different forms and might include samples, chemicals, devices, etc...

A usual practice in laboratories is to store the items in categories. Each category has attribute
definitions and each item in those categories should fit into those definitions. You can imagine
categories as database tables while attribute definitions are columns. Items are, in this case, the
rows in those database tables.

# Solution 

Lab-App is a Spring Boot REST API application which provides the following functionality:

* Creating categories with attribute definitions
* Creating items in those categories
* Updating items
* Getting items of a category

## Installation

Use the package manager [pip](https://pip.pypa.io/en/stable/) to install foobar.

```bash
pip install foobar
```

## Usage

```python
import foobar

# returns 'words'
foobar.pluralize('word')

# returns 'geese'
foobar.pluralize('goose')

# returns 'phenomenon'
foobar.singularize('phenomena')
```

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
[MIT](https://choosealicense.com/licenses/mit/)
