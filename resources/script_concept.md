# Aggregator Script Concept
> The following text describes the concept behind the aggregator script the app needs to fetch all required information.

## Challenge
The offical API ([Swagger](https://autobahn.api.bund.dev), [openapi.yaml](https://autobahn.api.bund.dev/openapi.yaml)) does not support a "fetch 'em all" approach. To fetch all available and required information for the app, a lot of `id` based requests have to be made. These approach is not fisable for a mobile app to be as user interaction responsible as possible. Besids this fact, in a world in which this app gets thousands of users I would guess some rate limit or resource caps would be applied by the API.

Nevertheless, not every data point is required. For example the app targets consumer car drivers and not commercial truckers.

## Solution
The challengs requires a periodically (e.g. every 6h) fetching of all required information and store it as a single JSON file on a public accessible web resource. To avoid heavy data processing in each consumer, the script should also parsed and sanitized. 

## Idea of data structure

### autobahn
- id: String
- roadworks: [Roadworks]
- webcams: [Webcam]
- warnings: [Warning]
- closures: [Closure]
- charging_stations: [ChargingStation]

#### Calculations
- `roadworks` result of `autobahn.id/services/roadworks`
- `webcams` result of `autobahn.id/services/webcam`
- `warnings` result of `autobahn.id/services/warning`
- `closures` result of `autobahn.id/services/closures`
- `charging_stations` result of `autobahn.id/services/electric_charging_station`

### Roadwork
- id: String
- title: String
- subtitle: String
- start_date: Date
- start_coordinate: Coordinate
- center_coordinate: Coordinate
- end_coordinate: Coordinate
- is_blocked: Bool
- raw_descriptions: [String]

#### Calculations
- `start_date`: Evaluate if the value could be in the future, if its only in the past, ignore it.
- `start_coordinate`: first coordinate in touple list in `extent` property
- `end_coordinate`: last coordinate in touple list in `extent` property
- `center_coordinate`: `coordinate`  property or `point`?
- `raw_descriptions`: Content of `description` property. Contains a lot of dirty data, maybe sanitizing required

### Webcam
 - id: String
 - title: String
 - coordinate: Coordinate
 - thumbnail_url: String
 - stream_url: String

 ### Warning
 - id: String
 - title: String
 - subtitle: String
 - coordinate: Coordinate
 - raw_descriptions: [String]

 #### Calculations
- `raw_descriptions`: Content of `description` property. Contains a lot of dirty data, maybe sanitizing required

### Closure
 - id: String
 - type: String (predefined)
 - title: String
 - subtitle: String
 - coordinate: Coordinate
 - raw_descriptions: [String]

 #### Calculations
- `type`: Based on the `display_type` property which seems to be an enum
- `raw_descriptions`: Content of `description` property. Contains a lot of dirty data, maybe sanitizing required

### ChargingStation
 - id: String
 - type: String (predefined)
 - title: String
 - subtitle: String
 - coordinate: Coordinate
 - raw_descriptions: [String]

  #### Calculations
- `type`: Based on the `icon` property which seems to be an enum
- `raw_descriptions`: Content of `description` property. Contains a lot of dirty data, maybe sanitizing required

### Coordinate
- latitude: Double
- longitude: Double
- coordinate: Coordinate

## Idea for further enhancements
### Field: `raw_descriptions`
It seems that this list of strings have dependent on the objects the belong to a hard structure. Which could be a human-readable message of all other property of the object or contain more information than the other properties.

#### Example Roadwork

**JSON Data**
```
"description": [
    "Beginn: 29.06.2021 09:00",
    "Ende: 28.11.2021 17:00",
    "",
    "Art der Maßnahme:Asphaltdeckenerneuerung",
    "Einschränkungen:Es steht nur 1 Fahrstreifen zur Verfügung.\n\nVollsperrung der AS Eutin Ostseite vom 17.07.2021 - 15.09.2021.\n\nVollsperrung der AS Scharbeutz Ostseite vom 16.09.2021 - 17.11.2021.",
    "Maximale Durchfahrsbreite: 3.25\n"
  ],
```

**Row contents:**
1. Begin date text
2. Ednd date text
3. ?
4. Reasons string which is not part of the data (maybe the `icon` property contains)
5. Extended description
6. Warnings (max height, etc.)