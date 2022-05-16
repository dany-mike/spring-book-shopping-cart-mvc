Vue.component("book-card", {
  props: ["book"],
  template: `<div class="card">
        <div class="card-body">
            <h6 class="card-title">
                {{ player.name }}
            </h6>
            <p class="card-text">
                <div>
                    {{ player.description }}
                </div>
            </p>
        </div>
        </div>`,
});

const app = new Vue({
  el: "#contents",
  data: {
    players: [
      { id: "1", name: "My book 1", description: "My description 1" },
      {
        id: "2",
        name: "My book 2",
        description: "My description 2",
      },
    ],
  },
});
