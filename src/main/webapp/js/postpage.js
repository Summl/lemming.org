const lute = Lute.New();
const html2MdRenderer = {
    renderLinkDest: function (node, entering) {
        if (entering) {
            console.log('重写 LinkDest 节点', node.__internal_object__.typ, node.TokensStr(), entering);
            return [node.TokensStr(), Lute.WalkContinue]
        } else {
            return ["", Lute.WalkContinue]
        }
    },
    renderBang: function (node, entering) {
        if (entering) {
            console.log('重写 Bang 节点', node.TokensStr(), entering);
            return ["!", Lute.WalkContinue]
        } else {
            return ["", Lute.WalkContinue]
        }
    },
};
lute.SetJSRenderers({
    "renderers": {
        "HTML2Md": html2MdRenderer,
    }
});

function mdToHtml(markdownText) {
    return lute.MarkdownStr("", markdownText)
}
window.onload = function () {

}