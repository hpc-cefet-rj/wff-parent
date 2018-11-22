package model.entity.workflow.graph

// A ordem é muito importante pois isto é persistido como ordinal
enum class VertexType {
  NullVertex,
  UnresolvedRelation,
  Relation,
  InputRelation,
  LeftRelation,
  RightRelation,
  OutputRelation,
  InputAndOutputRelation,
  LeftAndOutputRelation,
  RightAndOutputRelation,
  OnlyOutputRelation,
  Activity,
  MapActivity,
  FlatMapActivity,
  FilterActivity,
  JoinActivity,
  ReduceByKey,
  GroupByActivity,
  WindowActivity,
  StreamActivity
}
