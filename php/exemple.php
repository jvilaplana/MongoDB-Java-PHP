<?php

$manager = new MongoDB\Driver\Manager('mongodb+srv://jordi:jordi123@cursnosql.saj8u.mongodb.net/admin?retryWrites=true&w=majority');

$filter = ['year' => ['$lte' => 1893]];

$options = [
    'projection' => ['_id' => 0, 'title' => 1],
    'sort' => ['year' => -1],
];

$query = new MongoDB\Driver\Query($filter, $options);

$cursor = $manager->executeQuery('sample_mflix.movies', $query);

foreach ($cursor as $document) {
    var_dump($document);
}

?>
