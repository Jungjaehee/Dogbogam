declare namespace kakao {
    namespace maps {
      class LatLng {
        constructor(latitude: number, longitude: number);
      }
      class Map {
        constructor(container: HTMLElement, options: object);
        setCenter(position: LatLng): void;
        panTo(position: LatLng): void;
      }
      class Marker {
        constructor(options: { position: LatLng; map: Map });
        setMap(map: Map | null): void;
      }
      class InfoWindow {
        constructor(options: { content: string });
        open(map: Map, marker: Marker): void;
        close(): void;
      }
      namespace services {
        class Places {
          keywordSearch(
            keyword: string,
            callback: (result: PlacesSearchResult[], status: Status) => void
          ): void;
        }
        interface PlacesSearchResult {
          place_name: string;
          x: string; // 경도
          y: string; // 위도
        }
        type Status = 'OK' | 'ZERO_RESULT' | 'ERROR';
      }
      namespace event {
        function addListener(target: Marker, type: string, callback: () => void): void;
      }
    }
  }
  