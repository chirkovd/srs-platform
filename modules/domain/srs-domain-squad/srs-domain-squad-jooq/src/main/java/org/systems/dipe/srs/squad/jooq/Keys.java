/*
 * This file is generated by jOOQ.
 */
package org.systems.dipe.srs.squad.jooq;


import org.jooq.ForeignKey;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;
import org.systems.dipe.srs.squad.jooq.tables.JEquipment;
import org.systems.dipe.srs.squad.jooq.tables.JMember;
import org.systems.dipe.srs.squad.jooq.tables.JSquad;
import org.systems.dipe.srs.squad.jooq.tables.records.JEquipmentRecord;
import org.systems.dipe.srs.squad.jooq.tables.records.JMemberRecord;
import org.systems.dipe.srs.squad.jooq.tables.records.JSquadRecord;


/**
 * A class modelling foreign key relationships and constraints of tables in
 * squads.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<JEquipmentRecord> PK_SQUADS_EQUIPMENT = Internal.createUniqueKey(JEquipment.EQUIPMENT, DSL.name("PK.squads.equipment"), new TableField[] { JEquipment.EQUIPMENT.INVENTORY_ID }, true);
    public static final UniqueKey<JMemberRecord> PK_SQUADS_MEMBER = Internal.createUniqueKey(JMember.MEMBER, DSL.name("PK.squads.member"), new TableField[] { JMember.MEMBER.MEMBER_ID }, true);
    public static final UniqueKey<JSquadRecord> PK_SQUADS_SQUAD = Internal.createUniqueKey(JSquad.SQUAD, DSL.name("PK.squads.squad"), new TableField[] { JSquad.SQUAD.SQUAD_ID }, true);

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------

    public static final ForeignKey<JEquipmentRecord, JSquadRecord> EQUIPMENT__FK_SQUADS_EQUIPMENT_SQUAD = Internal.createForeignKey(JEquipment.EQUIPMENT, DSL.name("FK.squads.equipment.squad"), new TableField[] { JEquipment.EQUIPMENT.SQUAD_ID }, Keys.PK_SQUADS_SQUAD, new TableField[] { JSquad.SQUAD.SQUAD_ID }, true);
    public static final ForeignKey<JMemberRecord, JSquadRecord> MEMBER__FK_SQUADS_MEMBER_SQUAD = Internal.createForeignKey(JMember.MEMBER, DSL.name("FK.squads.member.squad"), new TableField[] { JMember.MEMBER.SQUAD_ID }, Keys.PK_SQUADS_SQUAD, new TableField[] { JSquad.SQUAD.SQUAD_ID }, true);
}
